@echo off
setlocal

rem Executa o mvn clean install
echo Executando mvn clean install...
mvn clean install
if %errorlevel% neq 0 (
  echo mvn clean install falhou. Abortando o build do Docker.
  exit /b 1
)

rem Executa o docker build
echo Executando docker build...
docker build -t authenticator-service .
if %errorlevel% neq 0 (
  echo Docker build falhou.
  exit /b 1
)

echo Build concluído com sucesso!
endlocal
