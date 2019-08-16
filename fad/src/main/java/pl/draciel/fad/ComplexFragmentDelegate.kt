package pl.draciel.fad

import androidx.fragment.app.Fragment

interface ComplexFragmentDelegate<T : Any, F : Fragment> : FragmentDelegate<T, F> {

    fun test(item: T): Boolean

}
