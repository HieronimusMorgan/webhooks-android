# Webhooks-Android

A comprehensive library to integrate webhooks into your Android application.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Introduction

Webhooks-Android is a simple and powerful library designed to facilitate the integration of webhooks in your Android applications. This library provides easy-to-use methods to send and receive webhook payloads.

## Features

- Easy integration with minimal setup
- Support for various payload formats (JSON, XML, etc.)
- Asynchronous and synchronous request handling
- Retry mechanism for failed requests
- Detailed logging for debugging

## Installation

Add the following dependency to your `build.gradle` file:

dependencies {
    implementation 'com.example:webhooks-android:1.0.0'
}

## Usage

To send Webhook from Android use this code and method addField can be added according to development needs

### Sending a Webhook

<img src="https://github.com/HieronimusMorgan/webhooks-android/blob/development/screenshoot/webhook.png" title="Sending a Webhook" />


### Sending a Webhook with Confirmation Bottomsheet

<img src="https://github.com/HieronimusMorgan/webhooks-android/blob/development/screenshoot/webhook-bottomsheet.png" title="Sending a Webhook with Confirmation Bottomsheet" />

### Sending a Log Webhook with Confirmation Bottomsheet

Define Object SendLog to set Context and Url Webhook

<img src="https://github.com/HieronimusMorgan/webhooks-android/blob/development/screenshoot/sendLog.png" title="Define Object SendLog" />

You can used this code to send log to Webhook based on type log

<img src="https://github.com/HieronimusMorgan/webhooks-android/blob/development/screenshoot/sendLog-1.png" title="Define Object SendLog" />

## Contributing
Contributions are welcome! Please open an issue or submit a pull request for any improvements or bug fixes.

## License
This template provides a general structure for a `README.md` file for a webhook library for Android. Adjust the code snippets and sections as needed based on the actual implementation and features of the library.
