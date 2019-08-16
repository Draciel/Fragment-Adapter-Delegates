package pl.draciel.fad

import android.util.ArrayMap
import androidx.fragment.app.Fragment

class ClassTypeFragmentDelegateManager<T, F, D>(private val map: ArrayMap<Class<out T>, D>) :
    FragmentDelegateManager<T, F, D> where T : Any,
                                           F : Fragment,
                                           D : FragmentDelegate<T, F> {

    override fun delegateByItem(item: T): D {
        return map[item::class.java]
            ?: throw IllegalArgumentException("Delegate not registered for the item type ${item::class.java}")
    }

    class Builder<T : Any, F : Fragment, D : FragmentDelegate<T, F>>(size: Int? = null) {
        private val map: ArrayMap<Class<out T>, D> = if (size == null) ArrayMap() else ArrayMap(size)
        private var isBuilt: Boolean = false

        @Suppress("UNCHECKED_CAST")
        fun register(type: Class<out T>, delegate: FragmentDelegate<T, F>): Builder<T, F, D> {
            if (!isBuilt) {
                map[type] = delegate as D
            }
            return this
        }

        fun build(): ClassTypeFragmentDelegateManager<T, F, D> {
            isBuilt = true
            return ClassTypeFragmentDelegateManager(map)
        }
    }

}
