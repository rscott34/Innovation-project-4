import pandas as pd
from name import NAMES
from categories import CATEGORIES
from brand import BRAND
from description import DESCRIPTION

def generate_data():
    df = pd.DataFrame()

    # Add product ID's to dataframe.
    df['product_id'] = [i for i in range(0, 200, 1)]
    
    # Add product names to dataframe.
    names = list(NAMES.values())
    df['name'] = names

    # Add product category to dataframe.
    categories = list(CATEGORIES.values())
    df['category'] = categories

    # Add product brand to dataframe.
    brands = list(BRAND.values())
    df['brand'] = brands

    # Add product description to dataframe.
    descriptions = list(DESCRIPTION.values())
    df['description'] = descriptions
    
    return df

data = generate_data()
print(data)
data.to_csv("products.csv")