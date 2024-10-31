# CSV Combiner

CSV Combiner is a JavaFX application designed to streamline the process of combining multiple CSV files from a selected input directory into a single, consolidated output file. The application ensures that all files share the same header format and removes duplicate rows, offering an easy-to-use graphical interface for file selection, validation, and merging.

## Features
- **Directory Selection**: Choose input and output directories for the CSV files.
- **File Validation**: Ensures that all CSV files have a consistent structure based on headers.
- **File Merging**: Combines valid CSV files into one output file while removing duplicates.
- **UI Feedback**: Displays real-time file statuses (e.g., pending, valid, invalid, complete).
- **Reactive Design**: The interface updates automatically based on the application's state.

## Getting Started

### Prerequisites
- Java 11+
- JavaFX SDK (if not bundled with your Java installation)
- Apache Commons CSV (added as a dependency for CSV file handling)

### Installation
1. **Clone the repository**:
   ```bash
   git clone https://github.com/dustij/csv-combiner.git
   cd csv-combiner
   ```
2. **Set up dependencies** in your IDE (ensure JavaFX and Apache Commons CSV are included).

3. **Run the Application**:
   You can run the main application file, `App.java`, from your IDE.

### Directory Structure
```plaintext
.
|-- main
|   |-- java
|   |   |-- com
|   |   |   `-- dustijohnson
|   |   |       |-- App.java            # Entry point for the application
|   |   |       |-- Controller.java     # Handles user interactions and manages tasks
|   |   |       |-- FileStatus.java     # Represents each file's status
|   |   |       |-- Interactor.java     # Manages business logic between Model and Service
|   |   |       |-- Model.java          # Holds the application's state
|   |   |       |-- Service.java        # Provides CSV-related operations (validation, merging)
|   |   |       `-- ViewBuilder.java    # Builds the JavaFX UI
|   |   `-- module-info.java            # Module descriptor
|   `-- resources
|       `-- css
|           `-- csv-view.css            # CSS styling for the application's UI
```

### Classes and Responsibilities

#### 1. `App.java`
- The main class launching the JavaFX application.
- Sets up the main `Stage` and initiates the `Controller` to manage the view.

#### 2. `Controller.java`
- Coordinates interactions between the `Model`, `Interactor`, and `ViewBuilder`.
- Defines the main actions, such as choosing directories and initiating the merge process.

#### 3. `FileStatus.java`
- Represents individual CSV file statuses with properties such as `filePath`, `fileName`, and `status`.
- Supports real-time UI binding for status updates.

#### 4. `Interactor.java`
- Acts as a middle layer, handling tasks like directory selection and file validation.
- Communicates with the `Service` to retrieve and validate files, and merges them when requested.

#### 5. `Model.java`
- Manages the application's state, including directory paths, file statuses, and merge readiness.
- Uses observable properties for UI binding to display current information.

#### 6. `Service.java`
- Handles core CSV operations, such as listing files, validating headers, and merging files.
- Removes duplicate rows using a custom `Row` class to ensure uniqueness in the final output.

#### 7. `ViewBuilder.java`
- Builds the JavaFX UI components.
- Provides layout and styling, including directory selection buttons, a table view for file statuses, and the merge button.
- Dynamically binds UI components to the `Model` for reactive updates.

### Usage

1. **Open the Application**: Run the application from `App.java`. A window titled "CSV Combiner" will appear.
2. **Select Input Directory**: Click the "Input" button to choose a directory containing the CSV files you wish to merge.
3. **Select Output Directory**: Click the "Output" button to specify where the combined CSV file should be saved.
4. **View File Status**: After selecting directories, the application will validate each file, updating the status in real time.
5. **Merge Files**: Once all files are validated, click "Merge" to create a combined CSV file in the output directory.

### Styling
The application’s UI is styled using `csv-view.css` in the `resources/css` directory. You can modify this file to adjust the appearance of labels, buttons, and other elements.

## Contributions
Feel free to fork this repository and submit pull requests. Contributions are welcome!

## Contact
For any questions or feedback, please contact the project maintainer at [dusti@dustijohnson.com].

## License
This project is licensed under the MIT License.

