import csv
import re
import uuid
import json
from typing import List, AnyStr

# ---------------------------------------

countries = {}
init_db = open('init-db.sql', 'r+b')
init_db_content = init_db.readlines()
i = 0
for line in init_db_content:
    if 241 <= i <= 490:
        match = re.search(r"(\d+), \'(.*)\', \'(.*)\'", line.decode("UTF-8"))
        if match:
            country_id, country_code, country_name = match[1], match[2], match[3]
            countries[country_id] = country_name
    i += 1

init_db.close()

# ---------------------------------------

cities = {}
init_db = open('init-db.sql', 'r+b')
init_db_content = init_db.readlines()
i = 0
for line in init_db_content:
    if 493 <= i <= 25181:
        match = re.search(r"\((\d+), \'(.*)\', (\d+)\)", line.decode("UTF-8"))
        if match:
            city_id, city_name, country_id = match[1], match[2], match[3]
            if countries[country_id] in cities:
                cities[countries[country_id]][city_name] = int(city_id)
            else:
                cities[countries[country_id]] = {city_name: int(city_id)}
    i += 1

init_db.close()

content: List[AnyStr] = [
    b'INSERT INTO "airports" ("id", "iata_code", "icao_code", "latitude", "longitude", "name", "city_id") VALUES\n']

with open('apinfo.csv', newline='', encoding="ISO-8859-1") as csvfile:
    reader = csv.DictReader(csvfile, delimiter='|')
    for row in reader:
        if row['country_eng'] in cities:
            if row['city_eng'] in cities[row['country_eng']]:
                airport_id = uuid.uuid4()
                airport_iata_code = row['iata_code']
                if row['icao_code'] == '':
                    airport_icao_code = 'NULL'
                else:
                    airport_icao_code = "'" + row['icao_code'] + "'"
                airport_name = row['name_eng'].replace("'", "''")
                airport_latitude = row['latitude']
                airport_longitude = row['longitude']
                city_id = cities[row['country_eng']][row['city_eng']]

                content.append(("('%s', '%s', %s, (%f, %f), '%s', %d),\n" % (
                    airport_id, airport_iata_code, airport_icao_code, float(airport_latitude), float(airport_longitude),
                    airport_name, city_id)).encode("UTF-8"))

content[len(content) - 1] = content[len(content) - 1].decode("ISO-8859-1").replace(",\n", ";\n").encode("UTF-8")

file = open('init-db1.sql', 'r+b')

file.truncate(0)
file.writelines(content)

file.close()
