### Ссылка на демо:
https://drive.google.com/drive/folders/1RLnBfBbNcZ7cr478rKTnyDRxyZSAi-zQ?usp=sharing

### Как запустить:
1. Запустить локальный ktor сервер ru.aleksandra.hackavito.ApplicationKt из модуля server
2. Запустить composeApp для android, iosApp для iOS.

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

3. Запустить admin панель на wasmJS

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


### TODO:

- [x] Data class для большинства SDUI компонентов
- [x] Добавить навигацию по имени SDUI экрана
- [x] Добавить открытие браузера из SDUI
- [x] Добавить навигацию с параметрами
- [x] Добавить получение SDUIScreen по имени с сервера
- [x] Добавить UI экран корзины
- [x] Создать JSON для SDUI экрана корзины
- [ ] Добавить UI экран чекаута
- [ ] Создать JSON для SDUI экрана чекаута
- [ ] Добавить предзагрузку экрана, в которые можно снавигироваться
- [ ] Добавить кеширование экранов
- [x] Добавить админ панель
- [ ] Добавить аутентификацию
- [ ] Добавить no-code редактор для создания SDUI экранов
- [ ] Добавить возможность получать Json экрана используя Compose файл
- [x] Добавить редактор Json в админ панель
- [x] Добавить темную тему
- [x] Добавить локализацию
- [ ] Добавить аналитику
- [ ] Добавить тестирование
- [ ] Добавить создание Json с помощью искуственного интеллекта
- [x] Добавить возможности загружать только чаcть SDUI
- [ ] Добавить A/B тестирование в админ панель
- [ ] Добавить выборку для A/B тестирования в админ панель
- [ ] Добавить функцию отправки sdui только для тестовых аккаунтов
- [x] Добавить откат к предыдущей версии SDUI, как будто в гит
- [ ] Добавить возможность отключать SDUI и использовать нативный UI через feature флаги
- [ ] Добавить возможность выбирать, на каких типах устройств будет отображаться SDUI
- [ ] Сделать возможность отображения рандомного SDUI экрана из списка
- [ ] Добавить показ изменений с предыдущей версии SDUI и просмотр итогов аналитики
- [ ] Добавить получение базовой версии SDUI с сервера, если есть ошибка в JSON
- [ ] Добавить разный SDUI в зависимости от версии приложения на устройстве (например, чтобы от разной
версии бд все правильно отображалось)
- [ ] Добавить при неотображении экрана логи на сервер
- [ ] Добавить ролевую модель для админ панели
- [ ] Добавить команды для экранов, чтобы пользователи могли создавать экрана и менять только своей
команды (как будто это проект)
- [ ] Добавить локализацию в админ панель
- [ ] Продумать как передавать сложные данные в SDUI

## Что важно сказать:
Мы не стали делать темную тему для админ панели, так как сам сайт Авито не имеет темной темы.
Пользователи могут создавать экраны только для своей команды, так как над разными частями приложения
могут работать разные команды.