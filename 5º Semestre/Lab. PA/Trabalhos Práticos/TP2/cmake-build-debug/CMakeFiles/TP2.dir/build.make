# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.9

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /opt/clion-2017.3.3/bin/cmake/bin/cmake

# The command to remove a file.
RM = /opt/clion-2017.3.3/bin/cmake/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /home/geovane/CLionProjects/LPA/TP2

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /home/geovane/CLionProjects/LPA/TP2/cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/TP2.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/TP2.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/TP2.dir/flags.make

CMakeFiles/TP2.dir/main.c.o: CMakeFiles/TP2.dir/flags.make
CMakeFiles/TP2.dir/main.c.o: ../main.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/geovane/CLionProjects/LPA/TP2/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building C object CMakeFiles/TP2.dir/main.c.o"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles/TP2.dir/main.c.o   -c /home/geovane/CLionProjects/LPA/TP2/main.c

CMakeFiles/TP2.dir/main.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/TP2.dir/main.c.i"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E /home/geovane/CLionProjects/LPA/TP2/main.c > CMakeFiles/TP2.dir/main.c.i

CMakeFiles/TP2.dir/main.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/TP2.dir/main.c.s"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S /home/geovane/CLionProjects/LPA/TP2/main.c -o CMakeFiles/TP2.dir/main.c.s

CMakeFiles/TP2.dir/main.c.o.requires:

.PHONY : CMakeFiles/TP2.dir/main.c.o.requires

CMakeFiles/TP2.dir/main.c.o.provides: CMakeFiles/TP2.dir/main.c.o.requires
	$(MAKE) -f CMakeFiles/TP2.dir/build.make CMakeFiles/TP2.dir/main.c.o.provides.build
.PHONY : CMakeFiles/TP2.dir/main.c.o.provides

CMakeFiles/TP2.dir/main.c.o.provides.build: CMakeFiles/TP2.dir/main.c.o


# Object files for target TP2
TP2_OBJECTS = \
"CMakeFiles/TP2.dir/main.c.o"

# External object files for target TP2
TP2_EXTERNAL_OBJECTS =

TP2: CMakeFiles/TP2.dir/main.c.o
TP2: CMakeFiles/TP2.dir/build.make
TP2: CMakeFiles/TP2.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/home/geovane/CLionProjects/LPA/TP2/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking C executable TP2"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/TP2.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/TP2.dir/build: TP2

.PHONY : CMakeFiles/TP2.dir/build

CMakeFiles/TP2.dir/requires: CMakeFiles/TP2.dir/main.c.o.requires

.PHONY : CMakeFiles/TP2.dir/requires

CMakeFiles/TP2.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/TP2.dir/cmake_clean.cmake
.PHONY : CMakeFiles/TP2.dir/clean

CMakeFiles/TP2.dir/depend:
	cd /home/geovane/CLionProjects/LPA/TP2/cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/geovane/CLionProjects/LPA/TP2 /home/geovane/CLionProjects/LPA/TP2 /home/geovane/CLionProjects/LPA/TP2/cmake-build-debug /home/geovane/CLionProjects/LPA/TP2/cmake-build-debug /home/geovane/CLionProjects/LPA/TP2/cmake-build-debug/CMakeFiles/TP2.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/TP2.dir/depend

