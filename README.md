# Expense Tracker CLI 

This is an implementation of a simple Expense Tracker CLI application using Python. It allows users to manage their expenses, categorize them, and view reports.
The project details are mentioned on [Expense Tracker CLI](https://roadmap.sh/projects/expense-tracker) on [Roadmap.sh](https://roadmap.sh).

## Features
- Add expenses with categories
- View all expenses
- View expenses by category
- View total expenses
- Export expenses to a CSV file

## Installation
1. Clone the repository:
    ```bash
    git clone https://github.com/mohitk0208/GithubUserActivityCLI
    cd GithubUserActivityCLI
    ```
2. Create the jar file using Maven:
    ```bash
    mvn clean package
    ```
3. Run the application:
    ```bash
    java -jar target/expense-tracker-cli-1.0-SNAPSHOT.jar --help
    ```
   
## Usage
- To add an expense:
    ```bash
    java -jar target/expense-tracker-cli-1.0-SNAPSHOT.jar add --amount 100 --category "food" --description "Lunch at restaurant"
    ```
- To view all expenses:
    ```bash
    java -jar target/expense-tracker-cli-1.0-SNAPSHOT.jar list
    ```
- To view expenses by category:
    ```bash
    java -jar target/expense-tracker-cli-1.0-SNAPSHOT.jar list --category "food"
    ```
- To view total expenses:
    ```bash
    java -jar target/expense-tracker-cli-1.0-SNAPSHOT.jar summary
    ```
- To export expenses to a CSV file:
    ```bash
    java -jar target/expense-tracker-cli-1.0-SNAPSHOT.jar export --filename "data"
    ```


