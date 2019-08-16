package pl.draciel.fad

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

abstract class BasicFragmentStateAdapter<T, F, D>(
    fragmentManager: FragmentManager, lifecycle: Lifecycle,
    private val fragmentDelegateManager: FragmentDelegateManager<T, F, D>
) : FragmentStateAdapter(fragmentManager, lifecycle) where T : Any,
                                                           F : Fragment,
                                                           D : FragmentDelegate<T, F> {

    constructor(fragmentActivity: FragmentActivity, fragmentDelegateManager: FragmentDelegateManager<T, F, D>) : this(
        fragmentActivity.supportFragmentManager, fragmentActivity.lifecycle, fragmentDelegateManager
    )

    constructor(fragment: Fragment, fragmentDelegateManager: FragmentDelegateManager<T, F, D>) : this(
        fragment.childFragmentManager, fragment.lifecycle, fragmentDelegateManager
    )

    override fun createFragment(position: Int): Fragment {
        val item = getItem(position)
        return fragmentDelegateManager.delegateByItem(item).createFragment(item, position)
    }

    abstract fun getItem(position: Int): T

}
