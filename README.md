### Что необхдимо добавить:

[ ] Data class для большинства SDUI компонентов (пока мало)
[x] Добавить навигацию по имени SDUI экрана
[x] Добавить открытие браузера из SDUI
[ ] Добавить навигацию с параметрами
[ ] Добавить получение SDUIScreen по имени с сервера
[ ] Добавить UI экран корзины
[ ] Создать JSON для SDUI экрана корзины
[ ] Добавить UI экран чекаута
[ ] Создать JSON для SDUI экрана чекаута
[ ] Добавить предзагрузку экрана, в которые можно снавигироваться
[ ] Добавить кеширование экранов
[ ] Добавить админ панель
[ ] Добавить аутентификацию
[ ] Добавить no-code редактор для создания SDUI экранов
[ ] Добавить возможность получать Json экрана используя Compose файл
[ ] Добавить редактор Json в админ панель
[ ] Добавить темную тему
[ ] Добавить локализацию
[ ] Добавить аналитику
[ ] Добавить тестирование
[ ] Добавить создание Json с помощью искуственного интеллекта
[ ] Добавить возможности загружать только чаcть SDUI
[ ] Добавить A/B тестирование в админ панель
[ ] Добавить выборку для A/B тестирования в админ панель
[ ] Добавить функцию отправки sdui только для тестовых аккаунтов
[ ] Добавить откат к предыдущей версии SDUI, как будто в гит
[ ] Добавить возможность отключать SDUI и использовать нативный UI через feature флаги
[ ] Добавить возможность выбирать, на каких типах устройств будет отображаться SDUI
[ ] Сделать возможность отображения рандомного SDUI экрана из списка
[ ] Добавить показ изменений с предыдущей версии SDUI и просмотр итогов аналитики
[ ] Добавить получение базовой версии SDUI с сервера, если есть ошибка в JSON
[ ] Добавить разный SDUI в зависимости от версии приложения на устройстве (например, чтобы от разной версии бд все правильно отображалось)

This is a Kotlin Multiplatform project targeting Android, iOS, Web.

* [/composeApp](./composeApp/src) is for code that will be shared across your Compose Multiplatform
  applications.
  It contains several subfolders:
    - [commonMain](./composeApp/src/commonMain/kotlin) is for code that’s common for all targets.
    - Other folders are for Kotlin code that will be compiled for only the platform indicated in the
      folder name.
      For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
      the [iosMain](./composeApp/src/iosMain/kotlin) folder would be the right place for such calls.
      Similarly, if you want to edit the Desktop (JVM) specific part,
      the [jvmMain](./composeApp/src/jvmMain/kotlin)
      folder is the appropriate location.

* [/iosApp](./iosApp/iosApp) contains iOS applications. Even if you’re sharing your UI with Compose
  Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for
  your project.

### Build and Run Android Application

To build and run the development version of the Android app, use the run configuration from the run
widget
in your IDE’s toolbar or build it directly from the terminal:

- on macOS/Linux
  ```shell
  ./gradlew :composeApp:assembleDebug
  ```
- on Windows
  ```shell
  .\gradlew.bat :composeApp:assembleDebug
  ```

### Build and Run Web Application

To build and run the development version of the web app, use the run configuration from the run
widget
in your IDE’s toolbar or run it directly from the terminal:

- on macOS/Linux
  ```shell
  ./gradlew :composeApp:wasmJsBrowserDevelopmentRun
  ```
- on Windows
  ```shell
  .\gradlew.bat :composeApp:wasmJsBrowserDevelopmentRun
  ```

### Build and Run iOS Application

To build and run the development version of the iOS app, use the run configuration from the run
widget
in your IDE’s toolbar or open the [/iosApp](./iosApp) directory in Xcode and run it from there.

---

Learn more
about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html),
[Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform/#compose-multiplatform),
[Kotlin/Wasm](https://kotl.in/wasm/)…

We would appreciate your feedback on Compose/Web and Kotlin/Wasm in the public Slack
channel [#compose-web](https://slack-chats.kotlinlang.org/c/compose-web).
If you face any issues, please report them
on [YouTrack](https://youtrack.jetbrains.com/newIssue?project=CMP).