# 📝 Web TO DO List 📝

### 📚 Description

This is a simple web application that proposes the creation of to-do lists containing their respective tasks.

### 🎬 Preview

![Preview Video]()

### 📌 Features

In this application, the user can create a `ToDoList`, add `Todo` to it, edit and delete them, the same goes for the
`ToDoList`.

### 📦 Modeling

![Class Diagram](https://i.imgur.com/wdgo1Mn.png)

The application has two entities, `Todo` and `ToDoList`, which are related by a `OneToMany` relationship, where a `Todo`
can only belong to one `ToDoList` and a `ToDoList` can have many `Todo`.

### 💻 Technologies
* Java
* Spring Boot
* Spring Data JPA
* H2 Database
* Maven
* Thymeleaf
* HTML
* CSS
* Bootstrap

### 🎯 Future Features
* Add a `User` entity
* Add checkboxes to mark a `Todo` as completed
* Add start and end dates to `Todo`

### 📈 Next Development Steps
* Create unit and integration tests
* Dockerize the application
