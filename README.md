# McGill Augmented Reality App

## Description

For newcomers to McGill (or even grads.) it can be difficult to find your way to certain building on campus. In order to provide a solution to this problem we decided to create a react native mobile application in conjunction with java spring boot to leverage augmented reality to visualize directions to buildings on campus.

## Table of Contents

- [Demo](#demo)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)

## Demo

Coming soon.

## Features

- Select a building and visualize the path from your current location.
- Upload your class schedule to automatically populate buildings and course start and finish times.
- Receive notifications for when a course is about to start.
- Interactive maps and markers for a seamless user experience.
- Social aspect; including adding, removing, and seeing where friends are on campus.

## Installation

<Provide instructions on how to install and set up your app locally. Include any dependencies that need to be installed, and steps to run the app on a simulator or physical device. For example:>

1. Clone this repository: `git clone git@github.com:mattcab2002/mcgill-augmented-reality-app.git`
2. Checkout to frontend: `git checkout`
3. In 2 different windows open the frontend and the backend: `cd mcgill-augmented-reality-frontend` and `cd mcgill-augmented-reality-backend`
4. For the backend, modify the `application.properties` to match your postgres instance: `mcgill-augmented-reality-backend/src/main/resources/application.properties.example`
5. Start the backend: `./gradlew bootRun`
6. For the frontend, Install dependencies: `npm install`
7. Install Expo Go on your personal device `https://apps.apple.com/us/app/expo-go/id982107779`
8. Run the app (in `mcgill-augmented-reality-frontend/`): `npm run start`
  a. To run the app on your personal device scan the QR code that appears in the console with your device.
  b. Run the app on iOS simulator: `npx react-native run-ios`
  c. Run the app on Android emulator: `npx react-native run-android`

## Usage

Coming soon.

## Contributing

Contributions are welcome! If you find any issues or have suggestions for new features, please open an issue or submit a pull request. Make sure to follow the existing coding style and conventions.
