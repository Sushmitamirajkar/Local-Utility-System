@echo off
title LocalServe - Starting Server...
color 0A
echo.
echo  ========================================
echo   LocalServe - Local Service Provider
echo  ========================================
echo.
echo  Starting server, please wait...
echo.

d:
cd "d:\Internship Project\local-service-provider"
set "PATH=%PATH%;C:\Program Files\apache-maven-3.9.15\bin"

echo  Killing any process on port 8080...
for /f "tokens=5" %%a in ('netstat -aon ^| findstr :8080 2^>nul') do taskkill /F /PID %%a 2>nul

echo  Launching Spring Boot...
echo.
mvn spring-boot:run

pause
