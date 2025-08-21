@echo off
echo ========================================
echo    FURNITURE HOME PROJECT SETUP
echo ========================================
echo.

echo Checking prerequisites...
echo.

REM Check Java
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ Java not found! Please install Java 21
    echo Download from: https://adoptium.net/
    pause
    exit /b 1
) else (
    echo ✅ Java found
)

REM Check Maven
mvn -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ Maven not found! Please install Maven
    echo Download from: https://maven.apache.org/download.cgi
    pause
    exit /b 1
) else (
    echo ✅ Maven found
)

echo.
echo ========================================
echo Prerequisites check completed!
echo ========================================
echo.

echo Starting the application...
echo This will:
echo 1. Create the database (if it doesn't exist)
echo 2. Create all 13 tables
echo 3. Insert sample data
echo 4. Start the web application
echo.

echo Press any key to continue...
pause >nul

echo.
echo Starting Spring Boot application...
echo Application will be available at: http://localhost:8081
echo.

mvn spring-boot:run

pause
