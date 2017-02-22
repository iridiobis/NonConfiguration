# Non-Configuration
Classes and interfaces to provide fragments (or any other class that could need it) with the ability to keep an instance of any object on configuration changes.

##Background
Rotation and network calls have always been a bad mix in Android. There are multiple solutions and workarounds out there, but for me the best one consists in using MVP and keeping the presenter alive on configuration changes. This is really easy to achieve working with activities, as they provide the ``onRetainCustomNonConfigurationInstance()`` and ``getLastCustomNonConfigurationInstance()`` methods; but when working with fragments, this feature is not available.

The current library provides a set of classes and interfaces to bring this functionality to the fragments, so their presenters (components or whatever you want to keep alive) are not recreated on configuration changes.

## Notes
The library is only intended to ease the access to the custom non configuration instance to classes other than activities. This instance is lost if the activity is destroyed due to lack of resources or if we have selected the option to not keeping the activities (while in the same situations the state will be saved). The cache does not handle recovering the non-configuration instance from the saved state, although it is built to make it easy to the developer to implement the recovering (see _Recovering from saved instance state_ for more info).

## Installation
[![Maven Central][mavenbadge-svg]][mavenbadge]
[![Bintray](https://img.shields.io/bintray/v/iridiobis/maven/non-configuration.svg)](https://bintray.com/iridiobis/maven/non-configuration)

```gradle
compile "com.github.iridiobis:non-configuration:0.1"
```

## Usage
### Activity with custom non configuration instance

1) The activity has to extend the interface `ActivityWithConfiguration<ActivityComponent>`

2) Declare an instance variable for the core non configuration manager
```java
private CoreNonConfigurationManager<MainComponent> nonConfigurationManager;
```

3) Initialize the CoreNonConfigurationManager on create
```java
@Override
protected void onCreate(final Bundle savedInstanceState) {
    //fragments are recreated in super.oncreate, so we need this done before
    nonConfigurationManager = new CoreNonConfigurationManager<>(this, savedInstanceState);
    super.onCreate(savedInstanceState);
    ...
}
```

4) Save the state on save instance state
```java
@Override
public void onSaveInstanceState(final Bundle outState) {
    super.onSaveInstanceState(outState);
    nonConfigurationManager.saveState(outState);
}
```

5) Retain the custom non configuration instance
```java
@Override
public Object onRetainCustomNonConfigurationInstance() {
    return nonConfigurationManager.onRetainCustomNonConfigurationInstance();
}
```

### Activity with no custom non configuration instance

1) The activity has to extend the interface `HasNonConfigurationCache`

2) Declare an instance variable for the cache
``` java
private NonConfigurationCache nonConfigurationCache;
```

3) Initialize the cache on create
```java
@Override
protected void onCreate(final Bundle savedInstanceState) {
    //fragments are recreated in super.oncreate, so we need this done before
    nonConfigurationCache = new NonConfigurationCache(getLastCustomNonConfigurationInstance());
    super.onCreate(savedInstanceState);
    ...
}
```

4) Retain the custom non configuration instance
```java
@Override
public Object onRetainCustomNonConfigurationInstance() {
    return nonConfigurationManager.onRetainCustomNonConfigurationInstance();
}
```

### Fragments
1) The fragment must implement the interface `HasNonConfiguration<MyComponent>`

2) Declare an instance variable for the non configuration manager:
``` java
private NonConfigurationMaager<MyComponent> nonConfigurationManager;
```

3) Initialize the NonConfigurationManager in the `onCreate` or the `onViewCreated` of the Fragment
```java
@Override
public View onCreateView(final LayoutInflater inflater,
                         final ViewGroup container,
                         final Bundle savedInstanceState) {
    nonConfigurationManager = new NonConfigurationManager<>(
            (HasNonConfigurationCache) getActivity(),
            this,
            savedInstanceState);
    ...
}
```

4) Save the state on save instance state
```java
@Override
public void onSaveInstanceState(final Bundle outState) {
    super.onSaveInstanceState(outState);
    nonConfigurationManager.saveState(outState);
}
```

5) Destroy the non configuration instance on destroy
```java
@Override
public void onDestroy() {
    super.onDestroy();
    nonConfigurationManager.onDestroy();
}
```

### Recovering from saved instance state
The manager pass the saved instance state when creating the non-configuration instance.
If the state of the non-configuration was saved, we could recreate it instead of creating
a new one.
```java
@Override
public @NonNull MyComponent createNonConfiguration(@Nullable final Bundle savedInstanceState) {
    if (savedInstanceState == null)
        //return a new MyComponent
    else
        //create a new MyComponent using the state saved in savedInstanceState
}
```

## License

```
Copyright 2017 José Luis Benito Esteban

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

[mavenbadge-svg]: https://maven-badges.herokuapp.com/maven-central/com.github.iridiobis/non-configuration/badge.svg
[mavenbadge]: https://maven-badges.herokuapp.com/maven-central/com.github.iridiobis/non-configuration
[mavensearch]: http://search.maven.org/#search%7Cga%7C1%7Ccom.github.iridiobis%20non-configuration