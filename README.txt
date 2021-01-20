# Excel-Transfer
Program created for Luke

Program will ask you for the path of a:
	Codefile
	Codebook

Program stores values from the codefram file in a hashmap
It stores the number and the name in excel number is in the 'A' column and name is in 'B' column

It then opens the codebook file
The program looks in the 'D' column and checks each field whether the name matches to any of the files from the previous excel file which is codeframe
If it does it changes the 'E' column to the number corosponsing
Then the whole document is populated with data from codeframe and saved

IMPORTANT
The name needs to exactly match otherwise program wont match (including CAPS)
In codebook column 'E' all unused fields need to be / otherwise program wont recognise the field