# Car Search Automation 

This test automation is designed to search vehicle details using webuyanycar.com. Given an input file with car registration numbers, the suite extracts each registration number, retrieves vehicle details from the website, and compares the results against a provided output file.

## Features

1. **Input Processing:** Reads car registration numbers from an input file (`car_input.txt`) based on specific patterns.
2. **Web Interaction:** Navigates to website and performs vehicle detail searches using each extracted registration number with randomly generated mileage.
3. **Output Comparison:** Compares retrieved details with expected results from `car_output.txt`.
4. **Test Reporting:** Highlights and fails tests for any discrepancies between expected and actual details. (Cucumber Reporting Implemented)
5. **Extendable Design:** The suite is built to accommodate additional input files in the future.

## Tech Stack

- **Language:** Java 8
- **Build Tool:** Maven
- **Testing Framework:** JUnit
- **Behavior-Driven Development:** Cucumber
- **Design Patterns:** Page Object Model (POM) with Page Factory for efficient web element management and modular test structure

## Project Structure

- **src/main/java:** Contains core classes, utility functions, and page object models for interacting with the website.
- **src/test/java:** Contains Cucumber step definitions and test scenarios.

## Setup and Execution

### Prerequisites

- Java 8 or higher
- Maven

### Running Tests

1. **Clone the Repository**
   ```bash
   git clone https://github.com/Ola774/CarSearchAutomation.git
   cd CarSearchAutomation
   ```

2. **Install Dependencies**
   ```bash
   mvn clean install
   ```

3. **Execute Tests**
   ```bash
   mvn test -Dcucumber.options="--tags @carSearch"
   ```

### Test Configuration

- **Input File:** Place the `car_input.txt` file
- **Output File:** The `car_output.txt` file

## Design Patterns

The suite uses the **Page Object Model (POM)** to structure the website interaction layer, separating it from the business logic. This promotes reusability and readability. **Page Factory** is employed to optimise element initialization and encapsulate web elements.

## Detailed Test Flow

1. **File Parsing:** Reads `car_input.txt` to extract car registration numbers using pattern matching regex.
2. **Website Interaction:** For each registration number:
    - Navigates to the website.
    - Enters the registration number and a random mileage.
    - Retrieves vehicle details from the website.
3. **Comparison:** Compares the retrieved details with `car_output.txt`.
4. **Reporting:** Logs mismatches for easy debugging and fails the test if discrepancies are found.

## Example Output

After running tests, the console will output:
- **Match:** When expected and actual details align.
- **Mismatch:** When discrepancies are found, detailing the registration number, expected, and actual values.
- **Not Found:** When car reg can not be found in the output file.

## Extending the Suite

To add more input files in the future, simply create additional text files following the same format. The suite can be easily configured to read from these new files with minimal changes.


This suite showcases robust web automation and data-driven testing, providing an efficient and maintainable framework for verifying vehicle information with ease.
