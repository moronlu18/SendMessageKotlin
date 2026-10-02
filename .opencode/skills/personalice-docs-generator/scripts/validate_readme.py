import sys
import re

def validate_readme(file_path):
    try:
        with open(file_path, 'r', encoding='utf-8') as f:
            content = f.read()
    except Exception as e:
        print(f"Error reading file: {e}")
        return False

    success = True
    
    # 1. Exactly one # (H1) heading
    h1s = re.findall(r'^#\s+.+', content, re.MULTILINE)
    if len(h1s) != 1:
        print(f"Validation Error: Expected exactly one H1 header (#), found {len(h1s)}: {h1s}")
        success = False

    # 2. Check for key sections (case-insensitive)
    # We allow optional leading emojis or symbols
    key_sections = [
        (r'##\s+.*(?:Descripción|Description|Features|Características)', "Features/Description"),
        (r'##\s+.*(?:Arquitectura|Architecture|Tech Stack|Tecnologías)', "Architecture/Tech Stack"),
        (r'##\s+.*(?:Comenzando|Getting Started|🚀|🛠️)', "Getting Started/Setup")
    ]
    for pattern, name in key_sections:
        if not re.search(pattern, content, re.IGNORECASE):
            print(f"Validation Warning: Missing section related to '{name}'")
            # We treat missing sections as warnings, but can still return true if formatting is correct.
            
    return success

if __name__ == "__main__":
    if len(sys.argv) < 2:
        print("Usage: python validate_readme.py <path_to_readme>")
        sys.exit(1)
        
    path = sys.argv[1]
    if validate_readme(path):
        print("README.md validated successfully!")
        sys.exit(0)
    else:
        print("README.md has validation errors.")
        sys.exit(1)
