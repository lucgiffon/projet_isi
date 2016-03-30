if __name__ == '__main__':

	count = []
	twoBeThree = {}
	f = open("projet_ISI/src/main/resources/sources/cow.csv", 'r')
	line = f.readline()
	line = f.readline()
	while line != "":
		splitted_line = line.split(';')
		twoBeThree[splitted_line[0].strip()] = splitted_line[1].strip()
		line = f.readline()
	f.close()

	coords = {}
	f = open("projet_ISI/src/main/resources/sources/longlat.csv")
	line = f.readline()
	while line != "":
		try:
			splitted_line = line.split(",")
			coords[twoBeThree[splitted_line[0].strip()]] = (splitted_line[1].strip(), splitted_line[2].strip())
			line = f.readline()
		except Exception:
			line = f.readline()
			count.append(splitted_line[0].strip())
	f.close()

	countryNames = {}
	f = open("projet_ISI/src/main/resources/sources/Chomage.csv")
	f.readline()
	line = f.readline()
	while line != "":
		splitted_line = line.split(";")
		countryNames[splitted_line[1]] = splitted_line[0]
		line = f.readline()
	f.close()
			

	f = open("projet_ISI/src/main/resources/sources/CoordCountries.csv", "w")
	
	for c in sorted(coords.keys()):
		try:
			f.write(c + ";" + countryNames[c].replace("'", "\\\\'") + ";" + coords[c][0] + ";" + coords[c][1] + "\n")
		except Exception:
			continue
		
	f.close()

	#~ print(count)

