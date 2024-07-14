import os
import subprocess
import datetime

def main(file_path):
    base_dir = datetime.datetime.now().strftime('%Y%m%d-%H%M%S')
    os.makedirs(base_dir, exist_ok=True)
    
    parsed_data = parse_file(file_path)
    create_directory(parsed_data, base_dir)

def parse_file(file_path):
    with open(file_path, 'r') as file:
        lines = file.readlines()
    
    parsed_data = []
    stack = []

    for line in lines:
        indent_level = (len(line) - len(line.lstrip())) // 2
        item = line.strip()

        while len(stack) > indent_level:
            stack.pop()
        
        if indent_level == 0:
            stack = [item]
        else:
            stack = stack[:indent_level] + [item]
        
        parsed_data.append(tuple(stack))
    
    return parsed_data

def create_directory(parsed_data, base_dir):
    for path_parts in parsed_data:
        if len(path_parts) == 1:  # Уровень папки
            folder_path = os.path.join(base_dir, path_parts[0])
            os.makedirs(folder_path, exist_ok=True)
        elif len(path_parts) == 2:  # CN уровня
            folder_path = os.path.join(base_dir, path_parts[0], path_parts[1])
            os.makedirs(folder_path, exist_ok=True)
            cn = path_parts[1]
            cert_path = os.path.join(folder_path, f"{cn}.cer")
            create_certificate(cert_path, cn)
        elif len(path_parts) == 3:  # SAN уровня, но не создаем директории
            cn = path_parts[1]
            san = path_parts[2]
            cert_folder = os.path.join(base_dir, path_parts[0], cn)
            cert_path = os.path.join(cert_folder, f"{cn}.cer")
            if not os.path.exists(cert_path):  # Создаем сертификат только один раз
                create_certificate(cert_path, cn, san)

def create_certificate(cert_path, cn, san=None):
    openssl_path = './openssl.exe'  # Укажите правильный путь к openssl
    key_path = cert_path.replace('.cer', '.key')
    csr_path = cert_path.replace('.cer', '.csr')

    # Генерация ключа
    key_command = [
        openssl_path, 'genpkey', '-algorithm', 'RSA', '-out', key_path
    ]
    subprocess.run(key_command, check=True)

    # Генерация запроса на сертификат (CSR)
    csr_command = [
        openssl_path, 'req', '-new', '-key', key_path, '-out', csr_path,
        '-subj', f"/CN={cn}"
    ]

    if san:
        csr_command.extend(['-addext', f"subjectAltName=DNS:{san}"])

    subprocess.run(csr_command, check=True)

    # Самоподписанный сертификат
    cert_command = [
        openssl_path, 'req', '-x509', '-key', key_path, '-in', csr_path,
        '-out', cert_path, '-days', '365'
    ]
    subprocess.run(cert_command, check=True)

if __name__ == "__main__":
    file_path = 'certs.txt'
    main(file_path)
