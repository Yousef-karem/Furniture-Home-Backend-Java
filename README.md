# 🏠 Furniture Home - E-commerce Database Project

A complete Spring Boot application with a full e-commerce database structure for a furniture store.

## 🎯 **Project Overview**

This project includes:

- **13 database tables** for complete e-commerce functionality
- **User management** system
- **Product catalog** with images and reviews
- **Shopping cart** functionality
- **Order processing** system
- **Customer support** features

## 🛠️ **Prerequisites**

Before running this project, ensure you have:

- **Java 21** (JDK)
- **Maven 3.6+**
- **MySQL 8.0+** or **MariaDB 10.4+**
- **Git** (optional, for version control)

## 📋 **Installation Steps**

### **Step 1: Clone/Download Project**

```bash
# Option A: Clone from Git
git clone [your-repository-url]
cd Furniture-Home

# Option B: Extract downloaded ZIP file
# Navigate to extracted folder
```

### **Step 2: Install Java 21**

1. Download Java 21 from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://adoptium.net/)
2. Install and set JAVA_HOME environment variable
3. Verify installation: `java -version`

### **Step 3: Install Maven**

1. Download Maven from [Apache Maven](https://maven.apache.org/download.cgi)
2. Extract to a folder (e.g., `C:\Program Files\Apache\maven`)
3. Add Maven bin folder to PATH environment variable
4. Verify installation: `mvn -version`

### **Step 4: Install MySQL**

1. Download MySQL from [MySQL Downloads](https://dev.mysql.com/downloads/mysql/)
2. Install MySQL Server
3. Set root password (remember this!)
4. Start MySQL service

### **Step 5: Configure Database**

1. Open `src/main/resources/application.properties`
2. Update these values if needed:
   ```properties
   spring.datasource.username=root
   spring.datasource.password=YOUR_MYSQL_PASSWORD
   ```

## 🚀 **Running the Application**

### **Start the Application**

```bash
# Navigate to project directory
cd Furniture-Home

# Run the application
mvn spring-boot:run
```

### **What Happens Automatically**

1. ✅ **Database created:** `furniture home` (if it doesn't exist)
2. ✅ **All 13 tables created** with proper relationships
3. ✅ **Sample data inserted** (products, store settings)
4. ✅ **Application starts** on port 8081

## 🌐 **Accessing Your Database**

### **Web Endpoints**

- **Home:** `http://localhost:8081/`
- **Database Info:** `http://localhost:8081/database-info`
- **All Tables:** `http://localhost:8081/tables`
- **Database Visualization:** `http://localhost:8081/database-visual`
- **Sample Data:** `http://localhost:8081/sample-data`

### **Database Details**

- **Host:** localhost
- **Port:** 3306
- **Database:** `furniture home`
- **Username:** root
- **Password:** (what you set during MySQL installation)

## 🗄️ **Database Structure**

### **Core Tables**

- **`product`** - Furniture items catalog
- **`user`** - Customer and admin accounts
- **`cart`** - Shopping cart management
- **`order`** - Customer orders
- **`review`** - Product ratings and comments

### **Supporting Tables**

- **`image`** - Product photos
- **`enquiry`** - Customer support
- **`favourite`** - User wishlists
- **`storesetting`** - Store configuration

## 🔧 **Troubleshooting**

### **Common Issues**

#### **1. Port Already in Use**

```bash
# Change port in application.properties
server.port=8082
```

#### **2. Database Connection Failed**

- Ensure MySQL service is running
- Check username/password in `application.properties`
- Verify MySQL is accessible on localhost:3306

#### **3. Java Version Issues**

```bash
# Check Java version
java -version

# Should show Java 21.x.x
```

#### **4. Maven Issues**

```bash
# Clean and rebuild
mvn clean install
mvn spring-boot:run
```

### **Useful Commands**

```bash
# Check if MySQL is running
net start mysql

# Connect to MySQL
mysql -u root -p

# Show databases
SHOW DATABASES;

# Use furniture home database
USE `furniture home`;

# Show tables
SHOW TABLES;
```

## 📊 **Verifying Success**

After running the application, you should see:

1. **Console output:** "Started FurnitureHomeApplication"
2. **Database created:** `furniture home`
3. **13 tables created** with proper structure
4. **Sample data inserted**
5. **Web endpoints accessible** on localhost:8081


