package pl.draciel.fad

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle

open class ListFragmentStateAdapter<T, F, D>(
    fragmentManager: FragmentManager, lifecycle: Lifecycle,
    fragmentDelegateManager: FragmentDelegateManager<T, F, D>
) : BasicFragmentStateAdapter<T, F, D>(fragmentManager, lifecycle, fragmentDelegateManager)
        where T : Any,
              F : Fragment,
              D : FragmentDelegate<T, F> {

    constructor(fragmentActivity: FragmentActivity, fragmentDelegateManager: FragmentDelegateManager<T, F, D>) : this(
        fragmentActivity.supportFragmentManager, fragmentActivity.lifecycle, fragmentDelegateManager
    )

    constructor(fragment: Fragment, fragmentDelegateManager: FragmentDelegateManager<T, F, D>) : this(
        fragment.childFragmentManager, fragment.lifecycle, fragmentDelegateManager
    )

    private var items: List<T> = emptyList()

    fun setItems(items: List<T>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): T = items[position]

    override fun getItemCount(): Int = items.size

}
