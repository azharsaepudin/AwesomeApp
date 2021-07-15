# AwesomeApp
Awesome App have 2 module, module for App and Core. project specification Kotlin, MVVM, KOIN, Retrofit, Coroutine, Paging 3 jetpack.

Core module contents:
- domain layer and data layer (RemoteDataSource, Repository, model).
- CoreModule(Network Configuration).
- Implement Idling resource for instrument test. (Delete code idling resource on RemoteDataSource if you want to release).
- Dependencies put on shared_dependencies for all module app.

App Module Contents:
- App Module(Inject ViewModel Module, Inject UseCase Module).
- MyApplication run all module inject with koin.
- Create layout using material design and androidx.
- Instrument test.

