package pl.draciel.fad

import androidx.fragment.app.Fragment

interface FragmentDelegate<T : Any, F : Fragment> {

    fun createFragment(item: T, pos: Int): F

}
