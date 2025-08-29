<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Secure Data Sharing in Cloud</title>
  <style>
    body { font-family: "Segoe UI", sans-serif; background: #0f172a; color: #f1f5f9; margin: 0; }
    header { text-align: center; padding: 60px 20px; background: linear-gradient(135deg,#6366f1,#06b6d4); }
    header h1 { font-size: 2.5rem; margin: 0; }
    header p { margin: 8px 0 0; font-size: 1.2rem; opacity: 0.9; }
    .container { max-width: 900px; margin: auto; padding: 20px; }
    h2 { margin-top: 2rem; font-size: 1.8rem; border-bottom: 2px solid #334155; padding-bottom: 6px; }
    ul, ol { padding-left: 20px; }
    .card { background: #1e293b; padding: 16px; border-radius: 12px; margin-top: 1rem; box-shadow: 0 2px 8px rgba(0,0,0,0.25); }
    details { margin-top: 10px; border: 1px solid #334155; border-radius: 8px; padding: 8px; }
    summary { cursor: pointer; font-weight: bold; }
    pre { background: #0f172a; padding: 10px; border-radius: 6px; overflow-x: auto; border: 1px solid #334155; }
    footer { text-align: center; padding: 20px; margin-top: 40px; color: #94a3b8; font-size: 0.9rem; }
  </style>
</head>
<body>
  <header>
    <h1>🔐 Secure Data Sharing in Cloud</h1>
    <p>A cryptography-based approach with provenance tracking</p>
  </header>
  <div class="container">
    <h2>🔹 Overview</h2>
    <div class="card">
      <p>Secure cloud data sharing using <b>AES encryption</b>, <b>MySQL metadata storage</b>, 
      and a <b>Data Provenance system</b>. Files are stored on Azure while every access/update 
      is logged for transparency.</p>
    </div>

    <h2>🔹 Architecture</h2>
    <ul>
      <li>🔑 Encryption Module – AES file encryption</li>
      <li>🗄️ MySQL – stores metadata and keys</li>
      <li>☁️ Azure Storage – encrypted files</li>
      <li>📜 Provenance Service – logs and approvals</li>
      <li>🌐 WebGUI/API – user interface</li>
    </ul>

    <h2>🔹 Workflow</h2>
    <ol>
      <li>Owner encrypts file → uploads to Azure</li>
      <li>Metadata + key stored in MySQL</li>
      <li>User requests access → provenance checks permissions</li>
      <li>Edits require owner approval</li>
      <li>Provenance logs every action</li>
    </ol>

    <h2>🔹 Data Provenance</h2>
    <details>
      <summary>📂 Show Provenance Phases</summary>
      <pre>
1. Initialize Dcur in repository
2. User proposes changes
3. Owner approves/rejects
4. New version logged with signed confirmation
5. Immutable audit trail maintained
      </pre>
    </details>

    <h2>🔹 Security Notes</h2>
    <ul>
      <li>🔒 Encrypt client-side before upload</li>
      <li>🔑 Store wrapped AES keys (via HSM/KMS)</li>
      <li>🖊️ Sign provenance logs</li>
      <li>👥 Enforce RBAC & least privilege</li>
    </ul>

    <h2>🔹 Roadmap</h2>
    <ul>
      <li>Integrate KMS/HSM</li>
      <li>Ledger DB for append-only provenance</li>
      <li>CLI tool for uploads</li>
      <li>Advanced policy engine</li>
    </ul>
  </div>
  <footer>
    ✅ Built with ❤️ for secure & transparent cloud collaboration.
  </footer>
</body>
</html>
