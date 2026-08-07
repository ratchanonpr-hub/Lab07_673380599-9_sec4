# Lab 7 - Spring Boot Database

โปรเจกต์นี้เป็น Spring Boot Web Application สำหรับจัดการข้อมูลเกม (Game) โดยเชื่อมต่อกับ PostgreSQL Database และใช้ Thymeleaf สำหรับแสดงผลหน้าเว็บ

## 1. สิ่งที่ต้องมี

ก่อนใช้งาน ต้องติดตั้งสิ่งต่อไปนี้ในเครื่อง

* Java JDK
* PostgreSQL
* Maven
* VS Code หรือ IDE อื่น ๆ

ตรวจสอบ Java:

```bash
java -version
```

ตรวจสอบ Maven:

```bash
mvn -version
```

---

## 2. สร้าง Database ใน PostgreSQL

เปิด PostgreSQL / pgAdmin หรือใช้ `psql` แล้วสร้าง Database ชื่อ:

```sql
CREATE DATABASE lab7;
```

จากนั้นตรวจสอบว่า Database ถูกสร้างแล้ว

```sql
\l
```

ควรพบ Database:

```text
lab7
```

---

## 3. ตั้งค่า `application.properties`

เปิดไฟล์:

```text
src/main/resources/application.properties
```

แล้วแก้ให้ตรงกับ PostgreSQL ในเครื่องของเรา

ตัวอย่าง:

```properties
spring.application.name=demo

spring.datasource.url=jdbc:postgresql://localhost:5432/lab7
spring.datasource.username=postgres
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### สิ่งที่ต้องแก้

เปลี่ยน:

```properties
YOUR_PASSWORD
```

เป็น Password ของ PostgreSQL เช่น ถ้า Password คือ `1234`

```properties
spring.datasource.password=1234
```

ถ้าใช้ Username อื่น ให้แก้:

```properties
spring.datasource.username=ชื่อผู้ใช้
```

โดยทั่วไปถ้าใช้ PostgreSQL ที่ติดตั้งตามปกติ Username มักเป็น:

```text
postgres
```

---

## 4. ตรวจสอบ PostgreSQL

ก่อนรันโปรเจกต์ ต้องตรวจสอบว่า PostgreSQL Server กำลังทำงานอยู่

โปรเจกต์นี้ใช้:

```text
Host: localhost
Port: 5432
Database: lab7
Username: postgres
Password: Password ของ PostgreSQL
```

ถ้า PostgreSQL ใช้ Port อื่น ให้แก้เลข `5432` ใน `application.properties`

ตัวอย่าง:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5433/lab7
```

---

## 5. เปิดโปรเจกต์

เปิดโฟลเดอร์โปรเจกต์ `demo` ด้วย VS Code

โครงสร้างหลักของโปรเจกต์:

```text
demo
├── src
│   └── main
│       ├── java
│       │   └── com.example.demo
│       │       ├── controller
│       │       ├── model
│       │       ├── repository
│       │       ├── service
│       │       └── strategy
│       │
│       └── resources
│           ├── templates
│           │   └── games
│           └── application.properties
│
├── pom.xml
└── mvnw
```

---

## 6. รันโปรเจกต์

เปิด Terminal ใน VS Code

เข้าไปยังโฟลเดอร์โปรเจกต์:

```cmd
cd demo
```

จากนั้นใช้คำสั่ง:

```cmd
mvnw spring-boot:run
```

ถ้าใช้ Windows และคำสั่งด้านบนไม่ได้ สามารถใช้:

```cmd
.\mvnw.cmd spring-boot:run
```

หากรันสำเร็จ จะเห็นข้อความประมาณ:

```text
Started DemoApplication
```

---

## 7. เข้าใช้งานผ่าน Browser

หลังจากรันโปรเจกต์แล้ว เปิด Browser ไปที่:

```text
http://localhost:8081/games
```

> หมายเหตุ: Port อาจเป็น `8080` หรือ `8081` ขึ้นอยู่กับการตั้งค่าของโปรเจกต์ หาก Terminal แสดงว่า Tomcat ทำงานที่ Port ใด ให้ใช้ Port นั้น

ตัวอย่าง:

```text
http://localhost:8080/games
```

หรือ

```text
http://localhost:8081/games
```

---

## 8. การเพิ่มเกม

จากหน้า:

```text
/games
```

กดปุ่ม:

```text
เพิ่มเกมใหม่
```

กรอกข้อมูล:

* ชื่อเกม
* แนวเกม
* แพลตฟอร์ม
* คะแนน
* ราคาปกติ
* ส่วนลด
* วันวางจำหน่าย

ประเภทส่วนลดมี 3 แบบ:

```text
NONE
STUDENT
SEASONAL
```

โดยมีส่วนลด:

| ประเภท   | ส่วนลด |
| -------- | -----: |
| NONE     |     0% |
| STUDENT  |    10% |
| SEASONAL |    20% |

จากนั้นกด **บันทึก**

ข้อมูลจะถูกส่งไปยัง Spring Boot และบันทึกลง PostgreSQL

---

## 9. การแก้ไขและลบเกม

ในหน้า `/games` จะมีปุ่มจัดการข้อมูล

### แก้ไข

กดปุ่มแก้ไขของเกมที่ต้องการ

ระบบจะเปิด:

```text
/games/edit/{id}
```

แก้ไขข้อมูลแล้วกดบันทึก

### ลบ

กดปุ่มลบของเกมที่ต้องการ

ระบบจะเปิด:

```text
/games/delete/{id}
```

จากนั้นยืนยันการลบ

---

## 10. การหยุดโปรเจกต์

กลับไปที่ Terminal ที่กำลังรัน Spring Boot แล้วกด:

```text
Ctrl + C
```

---

## 11. หากพบ Error

### Database Connection Error

ตรวจสอบ:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/lab7
spring.datasource.username=postgres
spring.datasource.password=YOUR_PASSWORD
```

และตรวจสอบว่า PostgreSQL Server กำลังทำงานอยู่

### Port ถูกใช้งาน

ถ้า Port `8080` ถูกใช้งาน สามารถกำหนด Port ใหม่ใน `application.properties`:

```properties
server.port=8081
```

แล้วเข้า:

```text
http://localhost:8081/games
```

### Maven ไม่ทำงาน

แนะนำให้ใช้ Maven Wrapper ของโปรเจกต์:

```cmd
mvnw spring-boot:run
```

หรือบน Windows:

```cmd
.\mvnw.cmd spring-boot:run
```

---

## สรุปการใช้งานแบบสั้น

```text
1. เปิด PostgreSQL
       ↓
2. สร้าง Database ชื่อ lab7
       ↓
3. แก้ application.properties
       ↓
4. เปิด Terminal ในโฟลเดอร์ demo
       ↓
5. รัน mvnw spring-boot:run
       ↓
6. เปิด Browser
       ↓
7. http://localhost:8081/games
       ↓
8. เพิ่ม / แก้ไข / ลบข้อมูลเกม
```
