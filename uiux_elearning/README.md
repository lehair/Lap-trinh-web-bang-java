# 🎨 UI/UX E-Learning Platform

Đây là dự án nền tảng học tập trực tuyến (E-Learning) về thiết kế UI/UX. Dự án được xây dựng theo mô hình Client-Server với cấu trúc phân tầng (Layered Architecture). Đặc biệt, hệ thống giao tiếp với cơ sở dữ liệu bằng **JDBC thuần** (sử dụng DAO Pattern) mà không dùng ORM (như Hibernate), giúp tối ưu hiệu năng và quản lý truy vấn thủ công.

##  Công Nghệ Sử Dụng
- **Backend:** Java Spring Boot
- **Database:** MySQL (JDBC thuần API: `Connection`, `PreparedStatement`, `ResultSet`)
- **Frontend:** HTML, Thymeleaf, Bootstrap 5 (Giao diện đáp ứng - Responsive)
- **Testing:** JUnit 5, Mockito

## 🛠 Yêu Cầu Hệ Thống
- **Java:** JDK 17 trở lên
- **Database:** MySQL Server (có thể dùng XAMPP, MySQL Workbench, DBeaver)
- **IDE:** VS Code, IntelliJ IDEA, hoặc Eclipse

---

##  Hướng Dẫn Cài Đặt Và Chạy Dự Án

### Bước 1: Clone dự án về máy

### Bước 2: Thiết lập Cơ Sở Dữ Liệu (MySQL)
1. Mở MySQL (XAMPP hoặc ứng dụng tương đương).
2. Tìm thư mục `src/main/resources/` trong dự án và mở file `init.sql`.
3. Copy toàn bộ nội dung trong `init.sql` và chạy (Execute) trong MySQL để tự động tạo Database (`elearning_db`), tạo các bảng, và tự động nạp **dữ liệu mẫu (Sample Data)** cực kỳ đẹp mắt.

### Bước 3: Cấu hình kết nối Database
>  **Lưu ý quan trọng:** File `application.properties` đã bị ẩn (đưa vào `.gitignore`) vì lý do bảo mật. Người dùng sau khi clone dự án về **phải tự tạo file này**.

1. Vào thư mục `src/main/resources/`.
2. Tạo một file mới có tên là `application.properties`.
3. Copy và dán đoạn mã sau vào file vừa tạo (nhớ thay đổi tài khoản và mật khẩu MySQL cho đúng với máy của bạn):

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/elearning_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Thymeleaf properties
spring.thymeleaf.cache=false
spring.thymeleaf.mode=HTML
spring.thymeleaf.encoding=UTF-8
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html
```

### Bước 4: Chạy ứng dụng
- **Cách 1 (Dùng IDE - khuyên dùng):** Mở project bằng VS Code hoặc IntelliJ. Mở file `src/main/java/com/elearning/ElearningApplication.java` và nhấn nút **Run**.
- **Cách 2 (Dùng Maven ở Terminal):**
  ```bash
  mvn spring-boot:run
  ```

---

##  Hướng Dẫn Sử Dụng
Sau khi ứng dụng đã khởi động thành công trên cổng `8080`, bạn mở trình duyệt và truy cập:

-  **Giao diện Người Dùng (Học Viên):** [http://localhost:8080/](http://localhost:8080/)
  - Học viên có thể xem Danh mục khóa học, các Bài học nổi bật.
  - Xem chi tiết Video bài học, tải tài liệu đính kèm.
  - Làm bài thi Trắc nghiệm (Quiz) với cơ chế tự động chấm điểm và hiển thị giải thích đáp án.

-  **Giao diện Quản Trị (Admin Dashboard):** [http://localhost:8080/admin/danhmuc](http://localhost:8080/admin/danhmuc)
  - Cung cấp tính năng Full CRUD (Thêm, Sửa, Xóa) cực kỳ trực quan với chung một Form cho cả 3 trang: Danh Mục, Bài Học và Trắc Nghiệm.

##  Tác giả
Dự án được xây dựng cho mục đích học tập đồ án môn học.
