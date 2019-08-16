package pl.draciel.fad

import android.util.ArrayMap
import androidx.fragment.app.Fragment
import java.util.*

class MultiClassTypeFragmentDelegateManager<T, F, D> private constructor(
    private val map: ArrayMap<Class<out T>, List<D>>
) : FragmentDelegateManager<T, F, D> where T : Any,
                                           F : Fragment,
                                           D : ComplexFragmentDelegate<T, F> {

    override fun delegateByItem(item: T): D {
        val delegates = map[item::class.java]
        if (delegates.isNullOrEmpty()) {
            throw IllegalStateException("Delegate not registered for the item type ${item::class.java}")
        }
        return delegates.firstOrNull { it.test(item) }
            ?: throw IllegalStateException("Delegate not found for item type ${item::class.java}")
    }

    class Builder<T : Any, F : Fragment, D : ComplexFragmentDelegate<T, F>>(size: Int? = null) {
        private val map: ArrayMap<Class<out T>, MutableList<D>> = if (size == null) ArrayMap() else ArrayMap(size)
        private var isBuilt = false

        @Suppress("UNCHECKED_CAST")
        fun register(type: Class<out T>, vararg delegates: ComplexFragmentDelegate<out T, out F>): Builder<T, F, D> {
            if (!isBuilt) {
                var list = map[type]
                if (list == null) {
                    list = mutableListOf()
                    map[type] = list
                }
                list.addAll(delegates as Array<out D>)
            }
            return this
        }

        @Suppress("UNCHECKED_CAST")
        fun register(type: Class<out T>, delegates: List<ComplexFragmentDelegate<out T, out F>>): Builder<T, F, D> {
            if (!isBuilt) {
                var list = map[type]
                if (list == null) {
                    list = mutableListOf()
                    map[type] = list
                }
                list.addAll(delegates as List<D>)
            }
            return this
        }

        @Suppress("UNCHECKED_CAST")
        fun register(type: Class<out T>, delegate: ComplexFragmentDelegate<out T, out F>): Builder<T, F, D> {
            if (!isBuilt) {
                var list = map[type]
                if (list == null) {
                    list = mutableListOf()
                    map[type] = list
                }
                list.add(delegate as D)
            }
            return this
        }

        fun build(): MultiClassTypeFragmentDelegateManager<T, F, D> {
            isBuilt = true
            val copy = ArrayMap<Class<out T>, List<D>>(map.size)
            map.forEach { copy[it.key] = Collections.unmodifiableList(it.value) }
            return MultiClassTypeFragmentDelegateManager(copy)
        }
    }
}
