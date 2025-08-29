<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Secure Data Sharing in Cloud Environments – README</title>
  <meta name="description" content="Cryptography-based secure data sharing with provenance tracking on Azure, MySQL-backed auth, and AES encryption." />
  <style>
    :root {
      --bg: #0b0c10;
      --panel: #12141a;
      --muted: #9aa4b2;
      --text: #e6edf3;
      --brand: #7c3aed; /* violet */
      --brand-2: #00d4ff; /* cyan */
      --ok: #22c55e;
      --warn: #f59e0b;
      --danger: #ef4444;
      --code-bg: #0f172a;
      --border: #2a2f3a;
    }
    * { box-sizing: border-box; }
    html, body { margin: 0; padding: 0; background: linear-gradient(180deg, #0b0c10, #0b0c10 200px, #0e1116 100%); color: var(--text); font: 16px/1.55 system-ui, -apple-system, Segoe UI, Roboto, "Helvetica Neue", Arial, "Noto Sans", "Apple Color Emoji", "Segoe UI Emoji"; }
    a { color: var(--brand-2); text-decoration: none; }
    a:hover { text-decoration: underline; }
    .wrap { max-width: 1100px; margin: 0 auto; padding: 32px 20px 80px; }
    header.hero { display: grid; gap: 14px; padding: 28px; border: 1px solid var(--border); border-radius: 18px; background: radial-gradient(1200px 400px at 20% -10%, rgba(124,58,237,.2), transparent 60%), radial-gradient(800px 300px at 120% -20%, rgba(0,212,255,.18), transparent 60%), var(--panel); }
    header .title { font-size: 28px; font-weight: 800; letter-spacing: 0.2px; }
    header .subtitle { color: var(--muted); }
    .badges { display: flex; flex-wrap: wrap; gap: 8px; margin-top: 6px; }
    .badge { font-size: 12px; border: 1px solid var(--border); color: var(--muted); padding: 6px 10px; border-radius: 999px; background: rgba(255,255,255,0.02); }
    nav.toc { margin: 22px 0 26px; padding: 16px; border: 1px dashed var(--border); border-radius: 12px; background: rgba(124,58,237,0.06); }
    nav.toc a { color: var(--text); }
    nav.toc ol { margin: 0; padding-left: 20px; }

    section { margin-top: 28px; }
    h2 { font-size: 22px; margin: 0 0 10px; }
    h3 { font-size: 18px; margin: 20px 0 8px; }
    p { margin: 10px 0; }

    .grid { display: grid; gap: 16px; }
    @media (min-width: 900px) { .grid.cols-2 { grid-template-columns: 1fr 1fr; } }

    .card { border: 1px solid var(--border); background: var(--panel); border-radius: 14px; padding: 16px; }
    .kv { display: grid; grid-template-columns: 160px 1fr; gap: 8px 12px; }

    details { border: 1px solid var(--border); border-radius: 12px; padding: 14px 14px 0; background: rgba(255,255,255,0.02); }
    details > summary { cursor: pointer; font-weight: 600; padding: 6px 0 10px; }

    pre { background: var(--code-bg); color: #e2e8f0; border: 1px solid #1e293b; border-radius: 12px; padding: 14px; overflow: auto; position: relative; }
    code { font-family: ui-monospace, SFMono-Regular, Menlo, Monaco, Consolas, "Liberation Mono", "Courier New", monospace; font-size: 13px; }
    .copy { position: absolute; right: 10px; top: 8px; border: 1px solid var(--border); background: #0b1220; color: #dbeafe; padding: 4px 8px; border-radius: 8px; cursor: pointer; font-size: 12px; }

    .pill { display: inline-block; padding: 4px 8px; border-radius: 8px; border: 1px solid var(--border); color: var(--muted); }
    .list { padding-left: 18px; }

    footer { margin-top: 48px; color: var(--muted); text-align: center; }
  </style>
</head>
<body>
  <div class="wrap">
    <header class="hero">
      <div class="title">Secure Data Sharing in Cloud Environments</div>
      <div class="subtitle">A cryptography‑based approach with provenance tracking on Azure</div>
      <div class="badges">
        <span class="badge">AES Encryption</span>
        <span class="badge">Azure Storage</span>
        <span class="badge">MySQL Auth + Metadata</span>
        <span class="badge">Data Provenance</span>
      </div>
    </header>

    <nav class="toc">
      <strong>Contents</strong>
      <ol>
        <li><a href="#overview">Overview</a></li>
        <li><a href="#architecture">Architecture</a></li>
        <li><a href="#workflow">Workflow</a></li>
        <li><a href="#provenance">Data Provenance System</a></li>
        <li><a href="#setup">Setup</a></li>
        <li><a href="#usage">Usage</a></li>
        <li><a href="#security">Security Notes</a></li>
        <li><a href="#diagram">Architecture Diagram (SVG)</a></li>
        <li><a href="#roadmap">Roadmap</a></li>
        <li><a href="#license">License</a></li>
      </ol>
    </nav>

    <section id="overview" class="card">
      <h2>Overview</h2>
      <p>
        This repository demonstrates secure, accountable data sharing on the cloud using
        <strong>AES encryption</strong>, <strong>role‑based permissions</strong>, and a <strong>Data Provenance</strong> layer.
        Files are stored in Azure; credentials, metadata (file names, MAC addresses), and <em>AES‑wrapped keys</em>
        are stored in MySQL. Every read/write is tracked to maintain transparency and integrity.
      </p>
      <div class="grid cols-2">
        <div>
          <h3>Key Features</h3>
          <ul class="list">
            <li>End‑to‑end encryption (AES) before upload</li>
            <li>MySQL‑backed authentication & access control</li>
            <li>Owner‑approved collaborative edits</li>
            <li>Full provenance trail for all interactions</li>
          </ul>
        </div>
        <div>
          <h3>Stack</h3>
          <div class="kv">
            <div class="pill">Cloud</div><div>Azure Blob/Files</div>
            <div class="pill">Auth/DB</div><div>MySQL (users, owners, metadata, keys)</div>
            <div class="pill">Crypto</div><div>AES (file encryption), key wrapping</div>
            <div class="pill">Provenance</div><div>Action logs; SMTP notifications</div>
          </div>
        </div>
      </div>
    </section>

    <section id="architecture" class="card">
      <h2>Architecture</h2>
      <p><strong>Owner</strong> encrypts files locally → uploads to <strong>Azure</strong>. Metadata (file name, MAC, owner/user IDs, AES key material) is stored in <strong>MySQL</strong>. The <strong>Web/API</strong> mediates access, consulting <strong>Provenance</strong> for policy & history.</p>
      <pre><button class="copy" data-copy="arch">Copy</button><code id="arch">Components:
- Encryption Module: AES file encryption; key wrap/store
- Auth & Metadata (MySQL): users, owners, filenames, MAC, AES keys
- Cloud Storage (Azure): encrypted object storage
- Provenance Service: logs read/write/update; owner approvals; SMTP alerts
- Web/API Layer: handles GET/POST; enforces permission & provenance checks
</code></pre>
    </section>

    <section id="workflow" class="card">
      <h2>Workflow</h2>
      <ol class="list">
        <li><strong>Owner selects file</strong> → encrypts with AES; key wrapped and stored in MySQL with metadata.</li>
        <li><strong>Upload</strong> encrypted file to Azure; record <em>Dcur</em> (current view) in provenance.</li>
        <li><strong>User requests access</strong> → API authenticates; checks policy & provenance.</li>
        <li><strong>Read/Edit</strong>: if authorized, user downloads/decrypts or submits changes.</li>
        <li><strong>Approval</strong>: owner accepts/rejects changes; provenance logs decision with signed confirmation.</li>
        <li><strong>Update</strong>: new encrypted version uploaded; Dcur advanced; SMTP notifications optional.</li>
      </ol>
    </section>

    <section id="provenance" class="card">
      <h2>Data Provenance System</h2>
      <p>
        The provenance layer provides a verifiable trail of actions over outsourced encrypted data. It stores
        <em>Dcur</em> snapshots, tracks stakeholder submissions, and records owner approvals/rejections. SMTP can be
        used for notifications or immutable audit forwarding.
      </p>
      <pre><button class="copy" data-copy="prov">Copy</button><code id="prov">Phases:
1) Initialize Dcur in cloud repository
2) Stakeholder submits proposed change (write authorization required)
3) Owner reviews → accept / reject
4) Signed confirmation logged; new Dcur established on accept
5) All steps appended to provenance (time, actor, file, MAC, hash)
</code></pre>
    </section>

    <section id="setup" class="card">
      <h2>Setup</h2>
      <div class="grid cols-2">
        <div>
          <h3>Prereqs</h3>
          <ul class="list">
            <li>Azure account (Blob/Files)</li>
            <li>MySQL 8.x</li>
            <li>Language runtime of your API (Node/Python/Java) – optional sample below</li>
          </ul>
        </div>
        <div>
          <h3>MySQL: Minimal Schema (example)</h3>
          <pre><button class="copy" data-copy="sql">Copy</button><code id="sql">CREATE TABLE users (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  email VARCHAR(255) UNIQUE NOT NULL,
  password_hash VARBINARY(255) NOT NULL,
  role ENUM('OWNER','USER') NOT NULL,
  mac_address VARCHAR(64)
);

CREATE TABLE files (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  owner_id BIGINT NOT NULL,
  filename VARCHAR(255) NOT NULL,
  azure_uri TEXT NOT NULL,
  aes_key_cipher VARBINARY(512) NOT NULL, -- wrapped key
  file_hash CHAR(64) NOT NULL,            -- SHA-256 of ciphertext
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (owner_id) REFERENCES users(id)
);

CREATE TABLE provenance (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  file_id BIGINT NOT NULL,
  actor_id BIGINT NOT NULL,
  action ENUM('UPLOAD','READ','PROPOSE_EDIT','APPROVE','REJECT','UPDATE') NOT NULL,
  details JSON,
  occurred_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  signature VARBINARY(512),
  FOREIGN KEY (file_id) REFERENCES files(id),
  FOREIGN KEY (actor_id) REFERENCES users(id)
);</code></pre>
        </div>
      </div>
    </section>

    <section id="usage" class="card">
      <h2>Usage</h2>
      <h3>API sketch (pseudo)</h3>
      <pre><button class="copy" data-copy="api">Copy</button><code id="api">POST /api/files
- Auth owner → encrypt(file) → upload to Azure
- Store metadata + wrapped key in MySQL
- Log provenance: UPLOAD, Dcur=hash

GET /api/files/:id
- Auth user → check permission & provenance policy
- Return Azure signed URL; log READ

POST /api/files/:id/proposals
- User submits changes → PROPOSE_EDIT

POST /api/proposals/:id/approve|reject
- Owner decision → APPROVE/REJECT (signed)
- On approve: encrypt new version → UPDATE
</code></pre>
    </section>

    <section id="security" class="card">
      <h2>Security Notes</h2>
      <ul class="list">
        <li>Encrypt client‑side where possible; never store plaintext in cloud.</li>
        <li>Store only <em>wrapped</em> AES keys in DB; protect unwrap keys via KMS/HSM.</li>
        <li>Sign provenance entries to prevent tampering; consider append‑only store.</li>
        <li>Include device signals (e.g., MAC) only if compliant with privacy policy.</li>
        <li>Rotate keys and enforce least‑privilege RBAC.</li>
      </ul>
    </section>

    <section id="diagram" class="card">
      <h2>Architecture Diagram (SVG)</h2>
      <details open>
        <summary>Toggle SVG</summary>
        <div style="overflow:auto; border:1px solid var(--border); border-radius:12px; padding:8px; background:#0b0c10">
          <!-- Embedded SVG provided by user -->
          <div style="min-width:640px">
            <!-- BEGIN USER SVG -->
            <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:lucid="lucid" width="1700.62" height="1240.62"><g transform="translate(2260.5970791012674 1620)" lucid:page-tab-id="0_0"><path d="M-2049.55-1243.3c10.25 0 18.62-7.77 18.83-17.44v-2.15c-7.53-55.06-40.8-99.86-104.64-99.86-63.62 0-98.15 38-104.64 100.1-1.05 9.56 6.5 18.17 16.74 19.24h173.7z" stroke="#000" stroke-opacity="0" fill="url(#a)"/><path d="M-2135.34-1349.78c-11.78.08-21.87-2.7-31.7-8.8l31.38 77.22 31.26-76.68c-9.7 5.7-19.48 8.26-30.94 8.26z" stroke="#000" stroke-opacity="0" fill="#fff" fill-opacity=".8"/><path d="M-2076.56-1404.78c0 30.48-26.3 55.22-58.68 55.22-32.4 0-58.68-24.74-58.68-55.22s26.3-55.22 58.68-55.22c32.4 0 58.68 24.74 58.68 55.22z" stroke="#000" stroke-opacity="0" fill="url(#b)"/><path d="M-2004.6-1317.48v-.13c6.16-5.7 6.16-15.12 0-20.93l-27.1-26.22c-.1 0-.18-.13-.27-.26-6.15-5.56-16.03-5.56-22.1.25l-27.9 25.44c-6.23 5.8-6.23 15.23 0 21.17l22.45 21.44c.86.78 1.3 1.82 1.3 2.85v39.4c-.1 1.4.52 2.7 1.64 3.6l10.23 9.95c1.38 1.16 3.64 1.16 5.1 0l10.06-9.56 5.9-5.55c.78-.8.78-1.95 0-2.72l-4.33-4c-.87-.9-.87-2.2 0-2.98l4.33-4.13c.1 0 .17-.13.26-.26.6-.63.52-1.67-.26-2.3l-4.16-4.15c-.86-.77-.86-2.06 0-2.97l4.33-4v-.13c.78-.64.7-1.8 0-2.58l-5.9-5.56v-1.54zm-38.1-40.8c4.92.12 8.82 3.86 8.9 8.5.1 4.53-3.8 8.28-8.65 8.4h-.44c-5-.64-8.48-4.9-7.87-9.55.52-3.88 3.72-6.85 7.88-7.36z" stroke="#000" stroke-opacity="0" fill="url(#c)"/><path d="M-2050.32-1252.86c.4.36.87.54 1.42.54 1.08 0 1.95-.87 1.93-1.88v-32.18c.03-.65-.27-1.26-.84-1.7-.37-.2-.75-.32-1.2-.32-1.07.04-1.92.9-1.88 1.9v32.3c-.02.5.17.98.56 1.34z" stroke="#000" stroke-opacity="0" fill="#ff9300" fill-opacity=".75"/><path d="M-2064.8-1327.68v-.52c0-1.5.8-2.23 2.38-2.22l40.12.23c1.62.02 2.38.77 2.38 2.25v.53c0 1.48-.8 2.22-2.38 2.2l-40.12-.2c-1.62-.03-2.38-.77-2.38-2.26zM-2064.8-1319.54v-.52c0-1.5.8-2.23 2.38-2.22l40.12.23c1.62 0 2.38.75 2.38 2.24v.52c0 1.48-.8 2.22-2.38 2.2l-40.12-.22c-1.62 0-2.38-.75-2.38-2.24z" stroke="#000" stroke-opacity="0" stroke-width=".01" fill="#ff9300" fill-opacity=".75"/><use xlink:href="#d" transform="matrix(1,0,0,1,-2270,-1231) translate(73.50000000000001 32)"/><!-- SVG truncated for brevity in this preview; include full content in your repo if needed. --></g></svg>
            <!-- END USER SVG -->
          </div>
        </div>
      </details>
    </section>

    <section id="roadmap" class="card">
      <h2>Roadmap</h2>
      <ul class="list">
        <li>Integrate KMS/HSM for server‑side key unwrap</li>
        <li>Append‑only provenance store (e.g., ledger DB)</li>
        <li>CLI tooling for local encryption & uploads</li>
        <li>Fine‑grained policy engine & audit exports</li>
      </ul>
    </section>

    <section id="license" class="card">
      <h2>License</h2>
      <p>MIT (or your choice). Update <code>LICENSE</code> file accordingly.</p>
    </section>

    <footer>
      Built with ♥ for transparent, secure collaboration.
    </footer>
  </div>

  <script>
    document.querySelectorAll('.copy').forEach(btn => {
      btn.addEventListener('click', () => {
        const id = btn.getAttribute('data-copy');
        const el = document.getElementById(id);
        navigator.clipboard.writeText(el.textContent).then(() => {
          const prev = btn.textContent;
          btn.textContent = 'Copied!';
          setTimeout(() => btn.textContent = prev, 1200);
        });
      });
    });
  </script>
</body>
</html>
