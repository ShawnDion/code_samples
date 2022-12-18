'''
    Chun Sheung Ng
    NUID: 002911540
    03/31/22
    CS5001
    Project 8: Yearly Precipitation in Portland with Exception Handling (main file)
    - set up three functions to calculate the yearly precipitation in Portland or other cities selected
    - use try/except block to protect main and handle exceptions within functions
    - create custom errors to handle potential exceptions in the program
'''

import os.path as op

# custom class - create own exception for file not found
class FileNotFoundError(Exception):
    # write error message for custom error
    def __init__(self):
        self.message = "File is not found."

# custom class - create own exception for incorrect file format
class FileFormatError(Exception):
    # write error message for custom error
    def __init__(self):
        self.message = "File in incorrect format."


# calculate average with a list
def get_avg(list_num): 
    '''
    Function: calculate the average with provided numbers
    Param: a list with multiple floats
    Return: a float of average number
    '''
    # check type of input
    if type(list_num) != list:
        raise TypeError("Type error occurred in get_avg function as parameter sent is not a list.")

    # return 0 if the length is 0
    if len(list_num) == 0: 
        raise ZeroDivisionError("Empty data field. Number of divisor should not be Zero.")

    # initiate loop variable as a float for the sum
    total = 0.0
    # for loop for calculating total and average
    for i in range(len(list_num)):
        total += list_num[i]
        avg = total/len(list_num)

    # return calculated average
    return avg


# print calculated yearly average in new output file
def print_avgs(data, city, starting_year, out_file): 
    '''
    Function: print calculated average into new output file
    Params: a list - yealy data of precipitation, a string - city's name,
            an int - starting year, a string - output file name
    Return: nothing, create new output file containing the calculated yearly averages
    '''

    # check type of each parameter
    # raise TypeError if the parameter type is invalid
    # check if 'data' is a list
    if not isinstance(data, list): 
        raise TypeError("First argument in print_avgs is not a list.")
    # check if 'city' is a string
    if not isinstance(city, str): 
        raise TypeError("Second argument in print_avgs is not a string.")
    # check if 'starting_year' is an integer
    if not isinstance(starting_year, int): 
        raise TypeError("Third argument in print_avgs is not an integer.")
    # check if 'out_file' is a string
    if not isinstance(out_file, str): 
        raise TypeError("Fourth argument in print_avgs is not a string.")

    # open new file with name input in main
    out_file = open(out_file, "w")
    # initiate loop variable for years to calculate averages
    year = starting_year
    # write header in output file
    out_file.write(city + " Average Rainfall : \n")
    # for loops to access list-by-list in the bigger list
    for yearly_prcp in data:
        yearly_avg = get_avg(yearly_prcp)   # calculate averages with 'get_avg' function
        out_file.write(str(year) + " : " + str(f"{yearly_avg:.3f}\n")) # write yearly results in output file
        year += 1
    # close the new file being written
    out_file.close()


# main function
def main(): 
    '''
    Function: maintain control flow
    Param: nothing
    Return: nothing, just create a file with data needed according to city and year
    '''
    flag = True
    skip_line_count = 0
    while flag: 
        # user input for CSV file name
        file_name = input("Please enter a CSV data file : ")
        
        # create an empty list of lists for containing data of each year
        city_data = [[],[],[],[],[],[],[],[],[],[],[]]

        # start try/except block to protect main
        try: 
            # make sure the file is in CSV
            if file_name.endswith(".csv") == False : 
                # raise file format error if file is not ended with CSV
                raise FileFormatError
            elif op.exists(file_name) == False : 
                # raise file not found error if file not exists
                raise FileNotFoundError
            # open file and assign it to a variable to read through
            file = open(file_name, "r") 
            flag = False

            # initiate a loop variable
            temp = True
            # while loop to get correct user input for city
            while temp:
                # user input number 1-3 to choose a city from the menu
                selection = input('''
                Select a Maine city name from:
                1. PORTLAND
                2. FREEPORT
                3. BIDDEFORD
                ''')
                # ask for input again with selection not a number or in range
                if selection != "1" and selection != "2" and selection != "3" : 
                    print("Please enter a number in range 1 to 3.\n")
                    continue
                # set selection according to user input
                elif selection == "1": 
                    selection = "PORTLAND"
                elif selection == "2": 
                    selection = "FREEPORT"
                elif selection == "3": 
                    selection = "BIDDEFORD"
                # change loop variable to end loop
                temp = False

            # for loops reading data line-by-line in opened file
            for line in file:

                # split data in file by commas
                data = line.split(",")

                # capture data of city name each line from NAME column
                city = data[1].split(" ")[0].replace('"','')

                # capture data of years from DATE column
                # check if the data is all not alphabetic to avoid first row
                if data[3].strip('""''').isalpha() == False: 
                    year = int(data[3].split("-")[0].replace('"','')) # access data and change them into an integer

                # capture precipitation data from the PRCP column
                # try/except sub-block to catch & count empty fields and make sure the program to continue
                try: 
                # check if the data is all not alphabetic to avoid first row
                    if data[9].strip('""''').isalpha() == False: 
                        precip = data[9].strip('""''') # access precipitation data
                        # jump over and back to next loop when accessing empty precip cells
                        if precip == '':
                            # continue
                            skip_line_count += 1
                            raise ValueError
                
                except ValueError: 
                    continue
                
                # extract only lines that start with city name selected
                # append precipitation data into list of lists according to city and years
                if city == selection and year <= 2020: 
                    city_data[year-2010].append(float(precip))

            # input name for new output file
            out_file_name = input("Enter name of new file: ")
            # call function with necessary parameters to show results in new output file
            print_avgs(city_data, selection, 2010, out_file_name)
            
            # close file after using
            file.close()

        # set exceptions
        # print respective error messages
        # handle custom error - FileNotFoundError
        except FileNotFoundError as fe :
            print(fe.message)
            print("File not exists. Please enter an existing file in folder.")
        # handle custom error - FileFormatError
        except FileFormatError as ffe : 
            print(ffe.message)
            print("Unable to open file. Please enter a CSV file.")
        # handle TypeError - remind type of particular parameter
        except TypeError as te : 
            print(te)
        # handle ValueError - remind the right value of particular parameter
        except ValueError as ve : 
            print(ve)
        # handle ZeroDivisionError - remind divisor should not be '0'
        except ZeroDivisionError as zde: 
            print(zde)
            print("Please append elements for not having any empty lists.")

    # print the count of lines of data being skipped (lab requirement)
    print(skip_line_count, "lines of data have been skipped due to empty fields during the process.")

# call main function
main()