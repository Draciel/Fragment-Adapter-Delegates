package pl.draciel.fad

import androidx.fragment.app.Fragment

interface FragmentDelegateManager<T : Any, F : Fragment, D : FragmentDelegate<T, F>> {

    fun delegateByItem(item: T): D

}
