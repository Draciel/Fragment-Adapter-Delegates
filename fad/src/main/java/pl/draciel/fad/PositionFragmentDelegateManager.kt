package pl.draciel.fad

import androidx.fragment.app.Fragment

class PositionFragmentDelegateManager<F, D> private constructor(private val list: List<D>) :
    FragmentDelegateManager<Int, F, D> where F : Fragment,
                                             D : PositionFragmentDelegate<F> {

    override fun delegateByItem(item: Int): D {
        return list[item] ?: throw IllegalArgumentException("Delegate not registered for position ${item}")
    }

    fun size(): Int = list.size

    class Builder<F : Fragment, D : PositionFragmentDelegate<F>>(size: Int? = null) {
        private val list: MutableList<D> = if (size == null) ArrayList() else ArrayList(size)
        private var isBuilt: Boolean = false

        @Suppress("UNCHECKED_CAST")
        fun register(delegate: PositionFragmentDelegate<F>): Builder<F, D> {
            if (!isBuilt) {
                list.add(delegate as D)
            }
            return this
        }

        fun build(): PositionFragmentDelegateManager<F, D> {
            isBuilt = true
            return PositionFragmentDelegateManager(list)
        }
    }
}
