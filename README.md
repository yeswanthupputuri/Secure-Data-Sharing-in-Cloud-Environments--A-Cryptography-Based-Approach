<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Secure Data Sharing in Cloud – Project Overview</title>
  <style>
    body {
      margin: 0;
      font-family: "Segoe UI", Roboto, Helvetica, Arial, sans-serif;
      background: #0f172a;
      color: #f1f5f9;
      line-height: 1.6;
    }
    header {
      padding: 60px 20px;
      text-align: center;
      background: linear-gradient(135deg,#6366f1,#06b6d4);
      color: white;
    }
    header h1 { font-size: 2.5rem; margin: 0; }
    header p { margin-top: 10px; font-size: 1.2rem; opacity: 0.9; }

    .container { max-width: 960px; margin: 0 auto; padding: 20px; }

    h2 { margin-top: 2rem; font-size: 1.8rem; border-bottom: 2px solid #334155; padding-bottom: 6px; }
    h3 { margin-top: 1.2rem; font-size: 1.3rem; color: #38bdf8; }

    ul { padding-left: 20px; }
    li { margin-bottom: 6px; }

    .card {
      background: #1e293b;
      padding: 16px;
      border-radius: 12px;
      margin-top: 1rem;
      box-shadow: 0 2px 8px rgba(0,0,0,0.25);
    }

    details {
      background: #0f172a;
      border: 1px solid #334155;
      border-radius: 8px;
      padding: 10px 14px;
      margin-top: 12px;
    }
    summary { cursor: pointer; font-weight: 600; }

    pre {
      background: #0f172a;
      padding: 12px;
      border-radius: 8px;
      overflow-x: auto;
      font-size: 0.9rem;
      border: 1px solid #334155;
    }
    code { font-family: Consolas, monospace; color: #facc15; }
    footer {
      margin-top: 50px;
      text-align: center;
      padding: 20px;
      font-size: 0.85rem;
      color: #94a3b8;
    }
  </style>
</head>
<body>
  <header>
    <h1>Secure Data Sharing in Cloud Environments</h1>
    <p>A cryptography-based approach with provenance tracking</p>
  </header>

  <div class="container">
    <h2>🔹 Overview</h2>
    <div class="card">
      <p>This project demonstrates secure cloud data sharing using <b>AES encryption</b>, 
      <b>MySQL metadata storage</b>, and a <b>Data Provenance system</b>. 
      Files are stored on Azure while every access/update is logged for transparency.</p>
    </div>

    <h2>🔹 Architecture</h2>
    <ul>
      <li>Encryption Module – AES file encryption, key wrapping</li>
      <li>MySQL – stores users, metadata, and keys</li>
      <li>Azure Storage – encrypted files</li>
      <li>Provenance Service – logs and approvals</li>
      <li>WebGUI/API – user interface and access control</li>
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
      <summary>Show Provenance Phases</summary>
      <pre><code>
1) Initialize Dcur in repository
2) User proposes changes
3) Owner approves/rejects
4) New version logged with signed confirmation
5) Immutable audit trail maintained
      </code></pre>
    </details>

    <h2>🔹 Security Notes</h2>
    <ul>
      <li>Encrypt client-side before upload</li>
      <li>Store only wrapped AES keys (use HSM/KMS for unwrap)</li>
      <li>Sign provenance logs</li>
      <li>Enforce least privilege & RBAC</li>
    </ul>

    <h2>🔹 Roadmap</h2>
    <ul>
      <li>Integrate KMS/HSM for key unwrap</li>
      <li>Ledger DB for append-only provenance</li>
      <li>CLI tool for uploads</li>
      <li>Advanced policy engine</li>
    </ul>
  </div>

  <footer>
    Built with ❤️ for secure and transparent cloud collaboration.
  </footer>
</body>
</html>
