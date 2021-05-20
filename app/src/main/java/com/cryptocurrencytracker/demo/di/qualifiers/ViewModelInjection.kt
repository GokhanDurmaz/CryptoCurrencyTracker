package com.cryptocurrencytracker.demo.di.qualifiers

import javax.inject.Qualifier

/**
 * Created by gokhan on 2/6/21.
 */
@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.FIELD)
annotation class ViewModelInjection
