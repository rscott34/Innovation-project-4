import pandas as pd

num_products = 20
num_stages = 5
num_questions = 18
num_tiers = 3

'''
Create data for products.csv
'''

products = pd.DataFrame()

# Add product_id column.
product_ids = []
for i in range(num_products):
    product_ids.append(f'P{i:03}')
products['product_id'] = product_ids

# Add name column.
names = [
    # Add food products.
    'Olive Oil', 'Orange', 'Potato', 'Cheddar Cheese', 'Bread',
    # Add luxury products.
    'Gucci Handbag', 'Rolex Watch', 'Cartier Necklace', 'iPhone 17', 'Moët & Chandon Champagne',
    # Clothing products.
    'T-shirt', 'Hoodie', 'Jeans', 'Swim Shorts', 'Cap',
    # Home products.
    'Sofa', 'Desk', 'Coffee Machine', 'Pillows', 'Rug'
]
products['names'] = names

# Add category.
categories = []
for i in range(num_products):
    if i < 5:
        categories.append('FOOD')
    elif i < 10:
        categories.append('LUXURY')
    elif i < 15:
        categories.append('CLOTHING')
    else:
        categories.append('HOME')
products['category'] = categories

# Add brand.
for i in range(num_products):
    food_brands = ['Tesco'] * 5
    luxury_brands = [
        'Gucci', 'Rolex', 'Cartier', 'Apple', 'Moët & Chandon'
    ]
    clothing_brands = [
        'Next', 'Nicce', 'Levi', 'Quicksilver', 'Adidas'
    ]
    home_brands = [
        'John Lewis', 'Ikea', 'Nescafe', 'Dunelm', 'Fishpools'
    ]
brands = food_brands + luxury_brands + clothing_brands + home_brands
products['brand'] = brands

# Add description.
for i in range(num_products):
    food_descriptions = [
        'Olive oil from Spain',
        'Orange from Spain',
        'Sack of potatoes from United Kingdom',
        'Block of cheddar cheese from United Kingdom',
        'Loaf of bread from United Kingdom'
    ]
    luxury_descriptions = [
        'Gucci handbag from Italy',
        'Watch from Switzerland',
        'Jewelery from France',
        'Smartphone from China',
        'Champagne from France'
    ]
    clothing_descriptions = [
        'T-shirt from Bangladesh',
        'Hoodie from Vietnam',
        'Jeans from India',
        'Swim shorts from Indonesia',
        'Cap from United States of America'
    ]
    home_descriptions = [
        'Sofa from the United Kingdom',
        'Desk from Sweden',
        'Coffee Machine from Hungary',
        'Pillows from Vietnam',
        'Rug from the United Kingdom'
    ]
descriptions = food_descriptions + luxury_descriptions + clothing_descriptions + home_descriptions
products['description'] = descriptions

products.to_csv('products.csv', index=False)

'''
Create data for stages.csv
'''

stages = pd.DataFrame()

# Add stage_id, product_id, and stage_type columns.
stage_ids = []
product_ids = []
stage_types = []
stage_options = ["Raw Materials", "Processing", "Assembly", "Transport", "Retail"]
for i in range(0, num_products, 1):
    for j in range(1, num_stages + 1, 1):
        stage_id = f"S{i}-{j:03}"
        product_id = f"P{i:03}"
        stage_type = stage_options[j - 1]
        stage_ids.append(stage_id)
        product_ids.append(product_id)
        stage_types.append(stage_type)

stages['stage_id'] = stage_ids
stages['product_id'] = product_ids
stages['stage_type'] = stage_types

# Add location column.
locations = []
RAW_MATERIAL_LOCATIONS = {
    0: "Catalonia, Spain", 1: "Millares, Spain", 2: "Norfolk, UK", 3: "Bath, UK",
    4: "Coxheath, UK", 5: "Tuscany, Italy", 6: "Ghanzi, Botswana", 7: "Kimberley, South Africa",
    8: "Datong, China", 9: "Champagne, France", 10: "Dhaka, Bangladesh", 11: "Binh Long, Vietnam",
    12: "Thane, India", 13: "Jakarta, Indonesia", 14: "Dhaka, Bangladesh", 15: "Dhaka, Bangladesh",
    16: "Uppsala, Sweden", 17: "Harare, Botswana", 18: "Binh Long, Vietnam", 19: "Dhaka, Bangladesh",
}
PROCESSING_LOCATIONS = {
    0: "Tarragona, Spain", 1: "Valencia, Spain", 2: "Norfolk, UK", 3: "Swindon, UK",
    4: "Maidstone, UK", 5: "Florence, Italy", 6: "Geneva, Switzerland", 7: "Paris, France",
    8: "Beijing, China", 9: "Reims, France", 10: "Dhaka, Bangladesh", 11: "Ho Chi Minh City, Vietnam",
    12: "Mumbai, India", 13: "Jakarta, Indonesia", 14: "Dhaka, Bangladesh", 15: "Manchester, UK",
    16: "Stockholm, Sweden", 17: "Geneva, Switzerland", 18: "Ho Chi Minh City", 19: "Dhaka, Bangladesh",
}
ASSEMBLY_LOCATIONS = {
    0: "Tarragona, Spain", 1: "Valencia, Spain", 2: "London, UK", 3: "London, UK",
    4: "Maidstone, UK", 5: "Florence, Italy", 6: "Geneva, Switzerland", 7: "Paris France",
    8: "Beijing, China", 9: "Reims, France", 10: "Dhaka, Bangladesh", 11: "Ho Chi Minh City, Vietnam",
    12: "Mumbai, India", 13: "Paris, France", 14: "Detroit, Michigan, US", 15: "Manchester, UK",
    16: "Stockholm, Sweden", 17: "Geneva, Switzerland", 18: "Ho Chi Minh City", 19: "Chelmsford, UK",
}
TRANSPORT_LOCATIONS = {
    0: "London Gateway Port, UK", 1: "Dover, UK", 2: "National Road Network, UK", 3: "National Road Network, UK",
    4: "National Road Network, UK", 5: "London Heathrow Airport, UK", 6: "Dover, UK", 7: "London Gateway Port, UK",
    8: "London Gatwick Airport, UK", 9: "Dover, UK", 10: "London Heathrow Airport, UK", 11: "London Heathrow Airport, UK",
    12: "London Gatwick Airport, UK", 13: "London Gatwick Airport, UK", 14: "London Heathrow Airport, UK", 15: "National Road Network, UK",
    16: "London Heathrow Airport, UK", 17: "Dover, UK", 18: "London Heathrow Airport, UK", 19: "National Road Network, UK",
}
RETAIL_LOCATIONS = {
    0: "Tesco Supermarket, London, UK", 1: "Tesco Supermarket, London, UK", 2: "Tesco Supermarket, London, UK", 3: "Tesco Supermarket, London, UK",
    4: "Tesco Supermarket, London, UK", 5: "Gucci Retail Store, London, UK", 6: "Rolex Retail Store, London, UK", 7: "Cartier Retail Store, UK",
    8: "Apple Store, London, UK", 9: "Harrods, London, UK", 10: "Next Retail Store, London, UK", 11: "Nicce Clothing Store, London, UK",
    12: "Levi's Jeans Store, London, UK", 13: "Quicksilver Retail Store, London, UK", 14: "Adidas Retail Store, London, UK", 15: "John Lewis Store, London, UK",
    16: "Ikea Warehouse Store, London, UK", 17: "Nescafe Retail Store, London, UK", 18: "Dunelm Retail Store, London, UK", 19: "Fishpools Retail Store, London, UK",
}
for i in range(0, num_products, 1):
    locations.append(RAW_MATERIAL_LOCATIONS[i])
    locations.append(PROCESSING_LOCATIONS[i])
    locations.append(ASSEMBLY_LOCATIONS[i])
    locations.append(TRANSPORT_LOCATIONS[i])
    locations.append(RETAIL_LOCATIONS[i])

stages['location'] = locations

# Add start_date column.
start_dates = []
RAW_MATERIAL_START_DATES = {
    0: "15/3/2025", 1: "2/6/2025", 2: "21/1/2025", 3: "10/9/2025",
    4: "5/4/2025", 5: "29/7/2025", 6: "20/2/2025", 7: "12/5/2025",
    8: "8/8/2025", 9: "30/9/2025", 10: "15/3/2025", 11: "2/6/2025",
    12: "21/1/2025", 13: "10/9/2025", 14: "5/4/2025", 15: "29/7/2025",
    16: "20/2/2025", 17: "12/5/2025", 18: "8/8/2025", 19: "30/9/2025"
}
PROCESSING_START_DATES = {
    0: "17/3/2025", 1: "4/6/2025", 2: "23/1/2025", 3: "12/9/2025",
    4: "7/4/2025", 5: "31/7/2025", 6: "22/2/2025", 7: "14/5/2025",
    8: "10/8/2025", 9: "2/10/2025", 10: "17/3/2025", 11: "4/6/2025",
    12: "23/1/2025", 13: "12/9/2025", 14: "7/4/2025", 15: "31/7/2025",
    16: "22/2/2025", 17: "14/5/2025", 18: "10/8/2025", 19: "2/10/2025"
}
ASSEMBLY_START_DATES = {
    0: "19/3/2025", 1: "6/6/2025", 2: "25/1/2025", 3: "14/9/2025",
    4: "9/4/2025", 5: "2/8/2025", 6: "24/2/2025", 7: "16/5/2025",
    8: "12/8/2025", 9: "4/10/2025", 10: "19/3/2025", 11: "6/6/2025",
    12: "25/1/2025", 13: "14/9/2025", 14: "9/4/2025", 15: "2/8/2025",
    16: "24/2/2025", 17: "16/5/2025", 18: "12/8/2025", 19: "4/10/2025"
}
TRANSPORT_START_DATES = {
    0: "22/3/2025", 1: "9/6/2025", 2: "28/1/2025", 3: "18/9/2025",
    4: "12/4/2025", 5: "5/8/2025", 6: "26/2/2025", 7: "19/5/2025",
    8: "15/8/2025", 9: "7/10/2025", 10: "22/3/2025", 11: "9/6/2025",
    12: "28/1/2025", 13: "18/9/2025", 14: "12/4/2025", 15: "5/8/2025",
    16: "26/2/2025", 17: "19/5/2025", 18: "15/8/2025", 19: "7/10/2025"
}
RETAIL_START_DATES = {
    0: "25/3/2025", 1: "12/6/2025", 2: "31/1/2025", 3: "21/9/2025",
    4: "15/4/2025", 5: "8/8/2025", 6: "28/2/2025", 7: "22/5/2025",
    8: "18/8/2025", 9: "10/10/2025", 10: "25/3/2025", 11: "12/6/2025",
    12: "31/1/2025", 13: "21/9/2025", 14: "15/4/2025", 15: "8/8/2025",
    16: "28/2/2025", 17: "22/5/2025", 18: "18/8/2025", 19: "10/10/2025"
}
for i in range(0, num_products, 1):
    start_dates.append(RAW_MATERIAL_START_DATES[i])
    start_dates.append(PROCESSING_START_DATES[i])
    start_dates.append(ASSEMBLY_START_DATES[i])
    start_dates.append(TRANSPORT_START_DATES[i])
    start_dates.append(RETAIL_START_DATES[i])

stages['start_date'] = start_dates

# Add end_date column.
end_dates = [None] * num_products * num_stages
stages['end_date'] = end_dates

# Add descriptions column.
descriptions = []
RAW_MATERIAL_DESCRIPTIONS = {
    0: "Olives harvested from olive tree", 1: "Orange harvested from orange tree",
    2: "Potato harvested from potato field", 3: "Milk collected from dairy farm",
    4: "Wheat harvested from wheat field", 5: "Leather sourced from cattle",
    6: "Precious metals and gemstones mined", 7: "Precious metals and gemstones mined",
    8: "Electronic minerals mined", 9: "Grapes harvested from vineyard",
    10: "Cotton harvested from field", 11: "Cotton harvested from field",
    12: "Cotton harvested from field", 13: "Synthetic fibres produced",
    14: "Cotton harvested from field", 15: "Wood harvested from tree farm",
    16: "Wood harvested from tree farm", 17: "Metal and plastic materials sourced",
    18: "Fabric materials sourced", 19: "Wool sourced from sheep"
}
PROCESSING_DESCRIPTIONS = {
    0: "Olives pressed and oil captured", 1: "Orange washed and sprayed",
    2: "Potato washed and sprayed", 3: "Milk processed into cheddar cheese",
    4: "Wheat milled into flour", 5: "Leather cut, treated, and processed",
    6: "Gold refined and processed", 7: "Metals refined and gemstones cut",
    8: "Electronic components produced", 9: "Grapes fermented into champagne",
    10: "Cotton spun into fabric", 11: "Fabric cut and processed",
    12: "Fabric cut and processed", 13: "Fabric cut and processed",
    14: "Fabric cut and processed", 15: "Wood cut and processed",
    16: "Wood cut and processed", 17: "Machine components produced",
    18: "Fabric cut and processed", 19: "Wool cut and processed"
}
ASSEMBLY_DESCRIPTIONS = {
    0: "Oil packaged into bottles and into crates", 1: "Orange packed netting and into crates",
    2: "Potato packed into plastic bags and into crates", 3: "Cheese packaged into blocks",
    4: "Bread baked and packaged", 5: "Handbag assembled",
    6: "Watch assembled", 7: "Necklace assembled",
    8: "Phone assembled", 9: "Champagne bottled",
    10: "T-shirt stitched", 11: "Hoodie stitched",
    12: "Jeans stitched", 13: "Swim shorts stitched",
    14: "Cap stitched", 15: "Sofa assembled",
    16: "Desk assembled", 17: "Coffee machine assembled",
    18: "Pillows filled and stitched", 19: "Rug woven"
}
TRANSPORT_DESCRIPTIONS = {
    0: "Flown to United Kingdom", 1: "Orange shipped to United Kingdom",
    2: "Potatoes transported via UK road networks", 3: "Cheese transported via UK road networks",
    4: "Bread transported via UK road networks", 5: "Handbags shipped to United Kingdom",
    6: "Watches shipped to United Kingdom", 7: "Jewellery shipped to United Kingdom",
    8: "Phones shipped to United Kingdom", 9: "Champagne shipped to United Kingdom",
    10: "Clothing shipped to United Kingdom", 11: "Clothing shipped to United Kingdom",
    12: "Clothing shipped to United Kingdom", 13: "Clothing shipped to United Kingdom",
    14: "Clothing shipped to United Kingdom", 15: "Furniture transported via UK road networks",
    16: "Furniture shipped to United Kingdom", 17: "Coffee machines shipped to United Kingdom",
    18: "Pillows shipped to United Kingdom", 19: "Rugs transported via UK road networks"
}
RETAIL_DESCRIPTIONS = {
    0: "Displayed in supermarket", 1: "Displayed in supermarket",
    2: "Displayed in supermarket", 3: "Displayed in supermarket",
    4: "Displayed in bakery section", 5: "Displayed in luxury store",
    6: "Displayed in watch store", 7: "Displayed in jewellery store",
    8: "Displayed in electronics store", 9: "Displayed in alcohol section",
    10: "Displayed in clothing store", 11: "Displayed in clothing store",
    12: "Displayed in clothing store", 13: "Displayed in clothing store",
    14: "Displayed in clothing store", 15: "Displayed in furniture store",
    16: "Displayed in furniture store", 17: "Displayed in appliance store",
    18: "Displayed in home goods store", 19: "Displayed in furniture store"
}
for i in range(0, num_products, 1):
    descriptions.append(RAW_MATERIAL_DESCRIPTIONS[i])
    descriptions.append(PROCESSING_DESCRIPTIONS[i])
    descriptions.append(ASSEMBLY_DESCRIPTIONS[i])
    descriptions.append(TRANSPORT_DESCRIPTIONS[i])
    descriptions.append(RETAIL_DESCRIPTIONS[i])

# Add end_date column.
stages['end_date']

# Add description column.
stages['description'] = descriptions

# Add stage_name column.
stages['stage_name'] = [None] * num_products * num_stages

stages.to_csv('stages.csv', index=False)

'''
Create data for input_shares.csv
'''

input_shares = pd.DataFrame()

# Add input_id column.
num_origin_countries = 2
input_ids = []
for i in range(1, num_products * num_origin_countries + 1):
    input_ids.append(f"IN-{i:03}")
input_shares['input_id'] = input_ids

# Add product_id column.
product_ids = []
for i in range(num_products):
    for j in range(num_origin_countries):
        product_ids.append(f'P{i:03}')
input_shares['product_id'] = product_ids

# Add input_name column.
input_names = [
    "Spanish Origin Materials", "French Origin Materials",
    "Spanish Origin Materials", "French Origin Materials",
    "British Origin Materials", "Chinese Origin Materials",
    "British Origin Materials", "Chinese Origin Materials",
    "British Origin Materials", "Chinese Origin Materials",
    "Italian Origin Materials", "French Origin Materials",
    "Swiss Origin Materials", "Botswanan Origin Materials",
    "French Origin Materials", "South African Origin Materials",
    "Chinese Origin Materials", "American Origin Materials",
    "French Origin Materials", "Spanish Origin Materials",
    "Banglaseshi Origin Materials", "Vietnamese Origin Materials",
    "Vietnamese Origin Materials", "Bangladeshi Origin Materials",
    "Indian Origin Materials", "Vietnamese Origin Materials",
    "Indonesian Origin Materials", "Indian Origin Materials",
    "American Origin Materials", "Vietnamese Origin Materials",
    "British Origin Materials", "French Origin Materials",
    "Swedish Origin Materials", "Finnish Origin Materials",
    "Hungarian Origin Materials", "Botswanan Origin Materials",
    "Vietnamese Origin Materials", "Bangladeshi Origin Materials",
    "British Origin Materials", "Vietnamese Origin Materials"
]
input_shares['input_name'] = input_names

# Add country column.
countries = [
    "Spain", "France",
    "Spain", "France",
    "United Kingdom", "China",
    "United Kindom", "China",
    "United Kingdom", "China",
    "Italy", "France",
    "Switzerland", "Botswana",
    "France", "South Africa",
    "China", "USA",
    "France", "Spain",
    "Bangladesh", "Vietnam",
    "Vietnam", "Bangladesh",
    "India", "Vietnam",
    "Indonesia", "India",
    "USA", "Vietnam",
    "United Kingdom", "France",
    "Sweden", "Finland",
    "Hungary", "Botswana",
    "Vietnam", "Bangladesh",
    "United Kingdom", "Vietnam"
]
input_shares['country'] = countries

# Add percentage column.
percentages = [
    70, 30, 60, 40,
    90, 10, 90, 10,
    80, 20, 90, 10,
    40, 60, 50, 50,
    70, 30, 60, 40,
    70, 30, 80, 20,
    70, 30, 30, 70,
    50, 50, 50, 50,
    40, 60, 60, 40,
    50, 50, 80, 20
]
input_shares['percentages'] = percentages

input_shares.to_csv("input_shares.csv", index=False)

'''
Create data for evidence.csv
'''

evidence = pd.DataFrame()

# Add evidence_id column.
num_evidence_labels = 4
evidence_ids = []
for i in range(num_evidence_labels):
    evidence_id = f"E{i+1}"
    evidence_ids.append(evidence_id)
evidence['evidence_id'] = evidence_ids

# Add type column.
types = [
    "Certificate",
    "Certificate",
    "Certificate",
    "N/A"
]
evidence['type'] = types

# Add issuer column.
issuers = [
    "V1", "V1", "V1", "N/A"
]
evidence['issuer'] = issuers

# Add date column.
dates = [
    "2025-01-15", '2025-01-28', "2025-02-17", "N/A"
]
evidence['date'] = dates

# Add summary column.
summaries = [
    "Certificate for EU organic label",
    "Fair Labor Association (FLA) verification",
    "Certified Carbob Neutral Company",
    "N/A"
]
evidence['summary'] = summaries

# Add file_reference column.
file_references = [
    "assets/eu-organic-logo-600x400_0.png",
    "assets/FairLaborAccreditationBadge_ONLYFORAPPROVEDUSE-e1726245330900.png",
    "assets/Carbon-Neutral-Logo.jpg",
    "N/A"
]
evidence['file_reference'] = file_references

evidence.to_csv("evidence.csv", index=False)

'''
Create data for claims.csv
'''

claims = pd.DataFrame()

# Add claim_id column.
claim_ids = []
num_claims = 30
for i in range(num_claims):
    claim_id = f"C{i+1:03}"
    claim_ids.append(claim_id)
claims['claim_id'] = claim_ids

# Add product_id column.
product_ids = [
    "P000", "P000", "P001", "P001", "P002","P003", "P004", "P004","P005", "P006",
    "P006", "P007", "P008", "P009", "P009", "P010", "P010", "P011", "P012", "P012",
    "P013", "P013", "P014", "P015", "P016", "P016", "P017", "P017","P018", "P019",
]
claims['product_id'] = product_ids

# Add evidence_id column.
evidence_ids = [
    "E1", "E3", "E1", "E2", "E1", "E1", "E2", "E2", "E2", "E3",
    "E2", "E2", "E1", "E2", "E2", "E3", "E2", "E2", "E3", "E2",
    "E3", "E2", "E1", "E1", "E2", "E2", "E3", "E2", "E3", "E1"
]
claims['evidence_id'] = evidence_ids

# Add claim_type column.
claim_types = []
for id in evidence_ids:
    match id:
        case "E1":
            claim_types.append("Sustainability")
        case "E2":
            claim_types.append("Social Responsbility")
        case "E3":
            claim_types.append("Environmental")
        case "E4":
            claim_types.append("N/A")
claims['claim_type'] = claim_types

# Add claim_text column.
claim_texts = []
for id in evidence_ids:
    match id:
        case "E1":
            claim_texts.append("Organic Certified")
        case "E2":
            claim_texts.append("Fair Labour Certified")
        case "E3":
            claim_texts.append("Carbon Neutral Certified")
        case "E4":
            claim_texts.append("N/A")
claims['claim_texts'] = claim_texts

# Add confidence_label column.
confidence_labels = ["Verified"] * num_claims
claims['confidence_label'] = confidence_labels

# Add rationale column.
rationales = []
for id in evidence_ids:
    match id:
        case "E1":
            rationales.append("EU organic certificate attached")
        case "E2":
            rationales.append("FLA certificate attached")
        case "E3":
            rationales.append("Carbon Neutral Certificate attached")
        case "E4":
            rationales.append("N/A")
claims['rationale'] = rationales

claims.to_csv("claims.csv", index=False)

'''
Create data for verifiers.csv
'''

verifiers = pd.DataFrame()
verifiers['Verifier_ID'] = ["V1"]
verifiers['Username'] = ["demo"]
verifiers['Password'] = ["demo1"]

verifiers.to_csv("Verifiers.csv", index=False)

'''
Create data for QuestMission.csv
'''

quest_mission = pd.DataFrame()

# Add mission_id column.
mission_ids = []
for i in range(num_questions):
    mission_id = f"M{i+1:03}"
    mission_ids.append(mission_id)
quest_mission['mission_id'] = mission_ids

# Add product_ids column.
product_ids = [
    "P001", "P000", "P002", "P060",
    "P001", "P003", "P001", "P001",
    "P002", "P005", "P005", "P007",
    "P001", "P001", "P010", "P000",
    "P002", "P009"
]

quest_mission['product_id'] = product_ids

# Add tier column.
tiers = []
for i in range(num_tiers):
    for j in range(6):
        if i == 0:
            tiers.append("basic")
        elif i == 1:
            tiers.append("intermediate")
        elif i == 2:
            tiers.append("advanced")
quest_mission['tiers'] = tiers

# Add questions column.
questions = [
    "Which country does the Gucci handbag come from?",
    "What type of product is P000?",
    "Which claim is verified for product P002?",
    "Which luxury brand makes product P006?",
    "Is the 'Organic Certified' claim for P001 verified?",
    "Where are the potatoes for P002 harvested?",
    "What percentage of materials for P005 come from Italy?",
    "What percentage of materials for P013 come from India?",
    "Which country contributes less to P002?",
    "What percentage of materials for P007 come from South Africa?",
    "What percentage of materials for P018 come from Vietnam?",
    "Which country contributes 60% of materials for P009?",
    "What is the combined percentage of Spanish and French materials for P001?",
    "Which claim for P016 is verified?",
    "Which two countries contribute equally to P015?",
    "Based on stages data, how many days between harvesting and retail for P000?",
    "What evidence supports the Fair Labour claim for P001?",
    "Which country provides 60% of materials for P009?"
]
quest_mission['question'] = questions

# Create answers column.
answers = [
    "Italy",
    "Olive Oil",
    "Organic Certified",
    "Rolex",
    "Yes",
    "Norfolk UK",
    "90",
    "70",
    "China",
    "50",
    "50",
    "France",
    "100",
    "Fair Labour Certified",
    "United Kingdom and France",
    "10",
    "FLA certificate",
    "France"
]
quest_mission['answer'] = answers

# Add grading_type column.
grading_types = [
    "multiple_choice",
    "multiple_choice",
    "multiple_choice",
    "multiple_choice",
    "multiple_choice",
    "multiple_choice",
    "numeric",
    "numeric",
    "multiple_choice",
    "numeric",
    "numeric",
    "multiple_choice",
    "numeric",
    "multiple_choice",
    "multiple_choice",
    "numeric",
    "multiple_choice",
    "multiple_choice"
]
quest_mission['grading_type'] = grading_types

# Add options column for MC questions.
options = [
    "Italy,France,Spain,Germany",
    "Olive Oil,Orange,Strawberry,Mango",
    "Fair Labour,Organic,Carbon Neutral,Recycled",
    "Rolex,Gucci,Louis Vuitton,Chanel",
    "Yes,No",
    "Devon UK,Suffolk UK,Norfolk UK,Cheshire UK",
    None,
    None,
    "Spain,Portugal,China,Bangladesh",
    None,
    None,
    "Bangladesh,Thailand,India,France",
    None,
    "Organic Certified,Carbon Neutral Shipping,Fair Labour,Recycled",
    "Italy and Belgium,Spain and Portugal,United Kingdom and France,USA and Italy",
    None,
    "EU certificate,FLA certificate,Self declaration,None",
    "Egypt,France,Italy,Spain",
]
quest_mission['options'] = options

explanations = [
    "P005 product information shows that the handbag comes from Italy",
    "P000 is Olive Oil from Spain. View the product details page",
    "Claim C005 for P002 is verified with EU Organic certificate E1",
    "P006 is an Rolex watch from Switzerland",
    "Claim C003 for P001 is verified with EU organic certificate E1",
    "S2-001 shows raw materials from Norfolk, United Kingdom",
    "IN-011 shows Italian Origin Materials at 90%",
    "IN-028 shows Indian Origin Materials at 70%",
    "IN-005 shows UK at 90%, IN-006 shows China at 10%",
    "IN-016 shows South African Origin Materials at 50%",
    "IN-037 shows Vietnamese Origin Materials at 50%",
    "IN-019 shows French Origin Materials at 60%",
    "Spain (60%) + France (40%) = 100% of materials",
    "Claim C025 for P016 is marked as 'Verified'",
    "IN-031 and IN-032 show UK (50%) and France (50%) for P015",
    "S0-001 (15/3) to S0-005 (25/3) = 10 days. Check the timeline",
    "Evidence E2 is a Fair Labor Association (FLA) verification certificate",
    "IN-019 shows French Origin Materials at 60% for P009"
]
quest_mission['explanation'] = explanations


# Add anchor column.
anchors = [
    "#information",
    "#information",
    "#claims",
    "#information",
    "#claims",
    "#stages",
    "#origin",
    "#origin",
    "#origin",
    "#origin",
    "#origin",
    "#origin",
    "#origin",
    "#claims",
    "#origin",
    "#stages",
    "#evidence",
    "#origin"
]
quest_mission['anchor'] = anchors
quest_mission.to_csv("QuestMission.csv", index=False)

'''
Create data for IssueReport.csv
'''

issue_report = pd.DataFrame()

# Add issue_id column.
issue_report['issue_id'] = ['ISS_01']

# Add product_id column.
issue_report['product_id'] = ['P001']

# Add reported_by column.
issue_report['reported_by'] = ['Anonymous']

# Add type column.
issue_report['type'] = ['False Claim']

# Add description column.
issue_report['description'] = ['Stages display raw materials are from Millares, Spain, but they are from Valencia, Spain.']

# Add status column.
issue_report['status'] = ['Unresolved']

# Add resolution_note column.
issue_report['resolution_note'] = ['Noted by admin.']
issue_report.to_csv("IssueReport.csv", index=False)

'''
Create data for ChangeLog.csv
'''

change_log = pd.DataFrame()

# Add log_id column.
change_log['log_id'] = ['L_001']

# Add entity_type column.
change_log['entity_type'] = ['Claim']

# Add entity_id column.
change_log['entity_id'] = ['C001']

# Add Verifier_ID column.
change_log['Verifier_ID'] = ['V1']

# Add timestamp column.
change_log['timestamp'] = ['2025-02-18']

# Add change_summary column.
change_log['change_summary'] = ['Raw materials for P001 updated to Valencia, Spain']
change_log.to_csv("ChangeLog.csv", index=False)

'''
Create images.csv data
'''

images = pd.DataFrame()

# Add image_id column.
image_ids = []
for i in range(20):
    image_id = f"IMA-{i:03}"
    image_ids.append(image_id)
images['image_id'] = image_ids

# Add product_id column.
product_ids = []
for i in range(20):
    product_id = f"P{i:03}"
    product_ids.append(product_id)
images['product_id'] = product_ids

# Add file_location column.
file_locations = []
for i in range(20):
    file_location = f"assets/P{i:03}.png"
    file_locations.append(file_location)
images['file_location'] = file_locations

print(images)

images.to_csv("images.csv", index=False)