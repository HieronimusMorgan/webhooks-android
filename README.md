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

new WebhooksConfiguration(MainActivity.this, urlWehbook)
                            .setTitle("This is Title")
                            .setDescription("This is Description")
                            .addField(new Fields("Field 1", "Description Field 1"))
                            .build();

### Sending a Webhook with Confirmation Bottomsheet

new WebhooksConfiguration(MainActivity.this, urlWehbook)
                            .setTitle("Testing")
                            .setDescription("Description Testing")
                            .addField(new Fields("Field 1", "Description Field 1"))
                            .bottomSheet("Title Send Webhook", "Description Send Webhook")
                            .build();

## Contributing
Contributions are welcome! Please open an issue or submit a pull request for any improvements or bug fixes.

## License
This template provides a general structure for a `README.md` file for a webhook library for Android. Adjust the code snippets and sections as needed based on the actual implementation and features of the library.
