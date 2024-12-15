# Webhooks-Android

A comprehensive library to integrate webhooks into your Android application.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
  - [Sending a Webhook](#sending-a-webhook)
  - [Sending a Webhook with Confirmation Bottomsheet](#sending-a-webhook-with-confirmation-bottomsheet)
  - [Sending a Log Webhook with Confirmation Bottomsheet](#sending-a-log-webhook-with-confirmation-bottomsheet)
- [Contributing](#contributing)
- [License](#license)

## Introduction

Webhooks-Android is a simple and powerful library designed to facilitate the integration of webhooks in your Android applications. This library provides easy-to-use methods to send and receive webhook payloads. It is especially suited for developers who want seamless integration of webhook functionality with robust logging, error handling, and confirmation mechanisms.

---

## Features

- **Easy Integration**: Minimal setup to get started.
- **Payload Support**: Handle various payload formats (e.g., JSON, XML).
- **Retry Mechanism**: Automatic retries for failed requests.
- **Detailed Logging**: Comprehensive logs for debugging and tracing webhook events.
- **Confirmation Bottomsheet**: Optional confirmation dialog before sending webhooks.
- **Support for Log-Based Webhooks**: Efficiently send log data as webhooks.

---

## Installation

To integrate the library into your project, add the following dependency to your `build.gradle` file:

```gradle
dependencies {
    implementation 'com.github.HieronimusMorgan:webhooks-android:v1.0.0'
}
```

Sync your project to download the dependency.

---

## Usage

### Sending a Webhook

You can use the following code to send a webhook. The `addField` method allows you to customize the payload according to your needs:

```java
WebhooksConfiguration webhooksConfig = new WebhooksConfiguration(context, "https://discord.com/api/webhooks/your-webhook-url");
webhooksConfig.setTitle("Sample Title")
    .setDescription("Sample Description")
    .addField(new Fields("Key", "Value"))
    .build();
```

<img src="https://github.com/HieronimusMorgan/webhooks-android/blob/development/screenshoot/webhook.png" title="Sending a Webhook" />

---

### Sending a Webhook with Confirmation Bottomsheet

Use the `bottomSheet` method to display a confirmation dialog before sending the webhook:

```java
webhooksConfig.bottomSheet("Confirm Action", "Are you sure you want to send this webhook?").build();
```

<img src="https://github.com/HieronimusMorgan/webhooks-android/blob/development/screenshoot/webhook-bottomsheet.png" title="Sending a Webhook with Confirmation Bottomsheet" />

---

### Sending a Log Webhook with Confirmation Bottomsheet

1. Define the `SendLog` object to initialize the webhook context and URL:

<img src="https://github.com/HieronimusMorgan/webhooks-android/blob/development/screenshoot/sendLog.png" title="Define Object SendLog" />

2. Use this code to send logs to the webhook based on log type:

```java
webhooksConfig.sendLog();
```

<img src="https://github.com/HieronimusMorgan/webhooks-android/blob/development/screenshoot/sendLog-1.png" title="Send Log Webhook" />

---

## Contributing

Contributions are welcome! To contribute:

1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Submit a pull request with a detailed description of the changes.

For major changes, please open an issue to discuss your proposed enhancements.

---

## License

This project is licensed under the [MIT License](LICENSE). You are free to use, modify, and distribute this library as per the terms of the license.

---

Enhancements include detailed descriptions, improved formatting, and structured examples for better clarity and usability. Let me know if further refinements are needed!

