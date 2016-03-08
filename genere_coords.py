if __name__ == '__main__':

	count = []
	twoBeThree = {}
	f = open("sources/cow.csv", 'r')
	line = f.readline()
	line = f.readline()
	while line != "":
		splitted_line = line.split(';')
		twoBeThree[splitted_line[0].strip()] = splitted_line[1].strip()
		line = f.readline()
	f.close()

	coords = {}
	f = open("sources/longlat.csv")
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

	f = open("sources/coord_countries.csv", "w")
	for c in sorted(coords.keys()):
		f.write(c + ";" + coords[c][0] + ";" + coords[c][1] + "\n")

	f.close()

	print(count)

