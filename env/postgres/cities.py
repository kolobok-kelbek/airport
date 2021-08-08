import csv
import re
from typing import List, AnyStr

# ---------------------------------------

countries = {}
init_db = open('init-db.sql', 'r+b')
init_db_content = init_db.readlines()
i = 0
for line in init_db_content:
    if 235 <= i <= 482:
        match = re.search(r"(\d+), \'(.*)\', \'(.*)\'", line.decode("UTF-8"))
        country_id, country_code, country_name = match[1], match[2], match[3]
        countries[country_code] = int(country_id)
    i += 1

init_db.close()


file = open('init-db1.sql', 'r+b')

content: List[AnyStr] = [b'INSERT INTO "cities" ("id", "name", "country_id") VALUES\n']

cities = {}

i = 1
with open('apinfo.csv', newline='') as csvfile:
    reader = csv.DictReader(csvfile)
    for row in reader:
        if row['iso2'] in countries:
            city_name = row['city'].replace("'", "''")

            key = city_name + "_" + str(countries[row['iso2']])
            if key in cities:
                continue
            cities[key] = city_name

            content.append(("(%d, '%s', %d),\n" % (i, city_name, countries[row['iso2']])).encode("UTF-8"))
            i += 1

content[len(content) - 1] = content[len(content) - 1].decode("UTF-8").replace(",\n", ";\n").encode("UTF-8")

file = open('init-db1.sql', 'r+b')

file.truncate(0)
file.writelines(content)

file.close()
