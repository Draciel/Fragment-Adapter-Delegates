package pl.draciel.fad

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle

open class PositionFragmentStateAdapter<F, D>(
    fragmentManager: FragmentManager, lifecycle: Lifecycle,
    private val fragmentDelegateManager: PositionFragmentDelegateManager<F, D>
    ) : BasicFragmentStateAdapter<Int, F, D>(fragmentManager, lifecycle, fragmentDelegateManager)
        where F : Fragment,
              D : PositionFragmentDelegate<F> {

    constructor(
        fragmentActivity: FragmentActivity, fragmentDelegateManager: PositionFragmentDelegateManager<F, D>
    ) : this(fragmentActivity.supportFragmentManager, fragmentActivity.lifecycle, fragmentDelegateManager)

    constructor(fragment: Fragment, fragmentDelegateManager: PositionFragmentDelegateManager<F, D>) :
            this(fragment.childFragmentManager, fragment.lifecycle, fragmentDelegateManager)

    override fun getItem(position: Int): Int = position

    override fun getItemCount(): Int = fragmentDelegateManager.size()
}
