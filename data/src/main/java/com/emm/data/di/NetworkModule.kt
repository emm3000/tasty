package com.emm.data.di

import android.os.Build
import com.emm.data.source.api.RestClient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val EMULATOR_URL = "http://10.0.2.2:3000/api/"
private const val LOCAL_URL = "http://192.168.0.28:3000/api/"
private const val BASE_URL = "https://recipes-production-0f52.up.railway.app/api/"

val networkModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get(), get(named("URL"))) }
    single { provideApi(get()) }
    single(named("URL")) { provideURL() }
}


private fun provideRetrofit(okHttpClient: OkHttpClient, baseURL: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}

private fun provideOkHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .connectTimeout(180, TimeUnit.SECONDS)
        .readTimeout(180, TimeUnit.SECONDS)
        .build()
}

private fun provideURL(): String {
    return if (isEmulator()) {
        EMULATOR_URL
    } else LOCAL_URL
}

private fun provideApi(retrofit: Retrofit): RestClient = retrofit.create(RestClient::class.java)

private fun isEmulator(): Boolean {
    return ((Build.FINGERPRINT.startsWith("google/sdk_gphone_")
            && Build.FINGERPRINT.endsWith(":user/release-keys")
            && Build.MANUFACTURER == "Google"
            && Build.PRODUCT.startsWith("sdk_gphone_")
            && Build.BRAND == "google" && Build.MODEL.startsWith("sdk_gphone_")) //
            || Build.FINGERPRINT.startsWith("generic")
            || Build.FINGERPRINT.startsWith("unknown")
            || Build.MODEL.contains("google_sdk")
            || Build.MODEL.contains("Emulator")
            || Build.MODEL.contains("Android SDK built for x86") //bluestacks
            || "QC_Reference_Phone" == Build.BOARD && "Xiaomi" != Build.MANUFACTURER //bluestacks
            || Build.MANUFACTURER.contains("Genymotion")
            || Build.HOST.startsWith("Build") //MSI App Player
            || Build.BRAND.startsWith("generic")
            && Build.DEVICE.startsWith("generic")
            || Build.PRODUCT == "google_sdk"
            || System.getProperties()["ro.kernel.qemu"] === "1"
            || Build.BRAND.startsWith(
        "generic"
    ) && Build.DEVICE.startsWith("generic"))
}