package com.example.trip

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * A classe Application personalizada para a aplicação.
 * É o ponto de entrada para a injeção de dependência com Hilt.
 *
 * A anotação `@HiltAndroidApp` aciona a geração de código do Hilt, incluindo
 * uma classe base para a aplicação que serve como o contentor de dependências.
 * Esta classe deve ser declarada no ficheiro `AndroidManifest.xml`.
 */
@HiltAndroidApp
class TripApp : Application()