# Non-Configuration
Classes and interfaces to provide fragments (or any other class that could need it) with the ability to keep an instance of any object on configuration changes.

##Background
Rotation and network calls have always been a bad mix in Android. There are multiple solutions and workarounds out there, but for me the best one consist in using MVP and keeping the presenter alive on configuration changes. This is really easy to achieve working with activities, as they provide the ``onRetainCustomNonConfigurationInstance()`` and ``getLastCustomNonConfigurationInstance()`` methods; but when working with fragments, this feature is not available.

The current library provides a set of classes and interfaces to bring this functionality to the fragments, so their presenters (components or whatever you want to keep alive) are not recreated on configuration changes.

## Notes
The library is only intended to ease the access to the custom non configuration instance to classes other than activities. This instance is lost if the activity is destroyed due to lack of resources or if we have selected the option to not keeping the activities (while in the same situations the state will be saved). The cache does not handle recovering the non-configuration instance from the saved state, although it is build to make it easy to the developer to implement the recovering (see _Recovering from saved instance state_ for more info).

## Installation
TODO

## Usage
TODO

### Recovering from saved instance state
TODO


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