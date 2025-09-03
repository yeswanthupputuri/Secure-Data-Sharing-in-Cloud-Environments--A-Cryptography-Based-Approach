# 🔐 Secure Data Sharing in Cloud Environments
*A cryptography-based approach with provenance tracking*

---

## 🔹 Overview
This project demonstrates secure cloud data sharing using **AES encryption**,  
**MySQL metadata storage**, and a **Data Provenance system**.  
Files are stored on Azure while every access/update is logged for transparency.

---

## 🔹 Architecture
- 🔑 **Encryption Module** – AES file encryption, key wrapping  
- 🗄️ **MySQL** – stores users, metadata, and keys  
- ☁️ **Azure Storage** – encrypted files  
- 📜 **Provenance Service** – logs and approvals  
- 🌐 **WebGUI/API** – user interface and access control  

---

## 🔹 Workflow
1. Owner encrypts file → uploads to Azure  
2. Metadata + key stored in MySQL  
3. User requests access → provenance checks permissions  
4. Edits require owner approval  
5. Provenance logs every action  

---
## 🔹 Security Notes
- 🔒 Encrypt client-side before upload  
- 🔑 Store only wrapped AES keys  
- 🖊️ Sign provenance logs  
- 👥 Enforce least privilege & RBAC  

---

## 🔹 Roadmap
- 🔐 Integrate KMS/HSM for key unwrap  
- 📘 Ledger DB for append-only provenance  
- 💻 CLI tool for uploads  
- ⚙️ Advanced policy engine  

---

✅ *Built with it for secure and transparent cloud collaboration.*
