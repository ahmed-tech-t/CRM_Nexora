package com.example.crm_nexora.data.di.annotation

import jakarta.inject.Qualifier

/** App-lifetime CoroutineScope for work that must outlive a screen or service. */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApplicationScope
