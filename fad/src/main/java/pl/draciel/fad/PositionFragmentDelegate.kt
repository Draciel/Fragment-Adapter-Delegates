package pl.draciel.fad

import androidx.fragment.app.Fragment

interface PositionFragmentDelegate<F : Fragment> : FragmentDelegate<Int, F> {

    companion object {
        fun <F : Fragment> create(creator: () -> F): PositionFragmentDelegate<F> {
            return object : PositionFragmentDelegate<F> {
                override fun createFragment(item: Int, pos: Int): F = creator()
            }
        }
    }
}
