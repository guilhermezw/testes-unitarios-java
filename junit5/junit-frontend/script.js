

"use strict";


const API_PREFIX = "/calculadora";

const $ = (sel) => document.querySelector(sel);
const $$ = (sel) => document.querySelectorAll(sel);

function getBaseUrl() {
  const input = $("#baseUrl").value.trim();
  return (input || "http://localhost:8080").replace(/\/$/, "");
}

function apiUrl(path) {
  const url = getBaseUrl() + API_PREFIX + path;
  console.log("[CalcAPI] →", url);
  return url;
}

function setStatus(ok) {
  const dot = $("#statusDot");
  dot.className = "status-dot " + (ok ? "ok" : "err");
}

function showResult(boxId, data, isError = false) {
  const box = $("#" + boxId);
  box.className = "result-box visible " + (isError ? "error" : "success");

  if (isError) {
    box.innerHTML = `
      <div class="result-label">ERRO</div>
      <div class="result-value error-text">${escHtml(data)}</div>
    `;
  } else {
    const value = data?.result ?? data?.["result:"] ?? JSON.stringify(data);
    box.innerHTML = `
      <div class="result-label">RESULTADO</div>
      <div class="result-value">${formatNum(value)}</div>
    `;
  }
}

function formatNum(val) {
  if (typeof val === "number") {
    return Number.isInteger(val)
      ? val.toLocaleString("pt-BR")
      : val.toLocaleString("pt-BR", { maximumFractionDigits: 10 });
  }
  return String(val);
}

function escHtml(str) {
  return String(str)
    .replace(/&/g, "&amp;")
    .replace(/</g, "&lt;")
    .replace(/>/g, "&gt;");
}

function setLoading(btn, loading) {
  if (loading) {
    btn.classList.add("loading");
    btn.dataset.original = btn.textContent;
    btn.innerHTML = `<span class="spinner"></span> CALCULANDO...`;
  } else {
    btn.classList.remove("loading");
    btn.textContent = btn.dataset.original || "CALCULAR";
  }
}


async function apiFetch(method, path, body) {
  const url = apiUrl(path);
  const opts = {
    method,
    headers: { "Content-Type": "application/json" },
  };
  if (body !== undefined) opts.body = JSON.stringify(body);

  const res = await fetch(url, opts);
  const json = await res.json().catch(() => null);

  if (!res.ok) {
    const msg =
      json?.message ||
      json?.error ||
      `HTTP ${res.status} — ${res.statusText}`;
    throw new Error(msg);
  }
  return json;
}


$$(".tab").forEach((tab) => {
  tab.addEventListener("click", () => {
    $$(".tab").forEach((t) => t.classList.remove("active"));
    $$(".panel").forEach((p) => p.classList.remove("active"));
    tab.classList.add("active");
    const target = document.getElementById("tab-" + tab.dataset.tab);
    if (target) target.classList.add("active");
  });
});


function setupOpButtons(containerSel) {
  $$(containerSel + " .op-btn").forEach((btn) => {
    btn.addEventListener("click", () => {
      $$(containerSel + " .op-btn").forEach((b) => b.classList.remove("active"));
      btn.classList.add("active");
    });
  });
}

setupOpButtons("#tab-operacoes");
setupOpButtons("#tab-porcentagem");


$("#btn-operacao").addEventListener("click", async () => {
  const btn = $("#btn-operacao");
  const numA = parseFloat($("#op-numA").value);
  const numB = parseFloat($("#op-numB").value);
  const opBtn = $("#tab-operacoes .op-btn.active");
  const operacao = opBtn?.dataset.op;

  if (isNaN(numA) || isNaN(numB)) {
    showResult("res-operacao", "Preencha Número A e Número B.", true);
    return;
  }

  if (operacao === "DIVISAO" && numB === 0) {
    showResult("res-operacao", "Divisão por zero não é permitida. Número B deve ser diferente de 0.", true);
    return;
  }

  setLoading(btn, true);
  try {
    const data = await apiFetch("POST", `/${operacao}`, { numeroA: numA, numeroB: numB });
    setStatus(true);
    showResult("res-operacao", data);
  } catch (err) {
    setStatus(false);
    showResult("res-operacao", err.message, true);
  } finally {
    setLoading(btn, false);
  }
});


$("#btn-porcentagem").addEventListener("click", async () => {
  const btn = $("#btn-porcentagem");
  const percentual = parseFloat($("#pct-percentual").value);
  const valor = parseFloat($("#pct-valor").value);
  const tipoBtn = $("#tab-porcentagem .op-btn.active");
  const tipo = tipoBtn?.dataset.tipo;

  if (isNaN(percentual) || isNaN(valor)) {
    showResult("res-porcentagem", "Preencha Percentual e Valor Base.", true);
    return;
  }

  setLoading(btn, true);
  try {
    const data = await apiFetch("POST", `/porcentagem/${tipo}`, { percentual, valor });
    setStatus(true);
    showResult("res-porcentagem", data);
  } catch (err) {
    setStatus(false);
    showResult("res-porcentagem", err.message, true);
  } finally {
    setLoading(btn, false);
  }
});



$("#btn-raiz").addEventListener("click", async () => {
  const btn = $("#btn-raiz");
  const raiz = parseFloat($("#raiz-num").value);

  if (isNaN(raiz) || raiz < 0) {
    showResult("res-raiz", "Informe um número válido (≥ 0).", true);
    return;
  }

  setLoading(btn, true);
  try {
    const data = await apiFetch("POST", "/raiz-quadrada", { raiz });
    setStatus(true);
    showResult("res-raiz", data);
  } catch (err) {
    setStatus(false);
    showResult("res-raiz", err.message, true);
  } finally {
    setLoading(btn, false);
  }
});



$("#btn-potencia").addEventListener("click", async () => {
  const btn = $("#btn-potencia");
  const base = parseFloat($("#pot-base").value);
  const expoente = parseFloat($("#pot-expoente").value);

  if (isNaN(base) || isNaN(expoente)) {
    showResult("res-potencia", "Preencha Base e Expoente.", true);
    return;
  }

  setLoading(btn, true);
  try {
    const data = await apiFetch("POST", "/potencia", { base, expoente });
    setStatus(true);
    showResult("res-potencia", data);
  } catch (err) {
    setStatus(false);
    showResult("res-potencia", err.message, true);
  } finally {
    setLoading(btn, false);
  }
});


$("#btn-historico").addEventListener("click", async () => {
  const btn = $("#btn-historico");
  const wrap = $("#res-historico");

  btn.classList.add("loading");
  btn.dataset.original = btn.textContent;
  btn.innerHTML = `<span class="spinner"></span> CARREGANDO...`;

  try {
    const data = await apiFetch("GET", "/historicos");
    setStatus(true);
    wrap.className = "history-table-wrap visible";

    if (!Array.isArray(data) || data.length === 0) {
      wrap.innerHTML = `<div class="empty-state">// nenhum histórico encontrado</div>`;
      return;
    }

    const rows = data
      .map(
        (item) => `
        <tr>
          <td>${escHtml(item.id ?? "—")}</td>
          <td>${escHtml(item.calculo ?? "—")}</td>
          <td class="op-badge">${escHtml(item.operacao ?? "—")}</td>
          <td class="num">${formatNum(item.resultado)}</td>
        </tr>`
      )
      .join("");

    wrap.innerHTML = `
      <table class="history-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Cálculo</th>
            <th>Operação</th>
            <th>Resultado</th>
          </tr>
        </thead>
        <tbody>${rows}</tbody>
      </table>`;
  } catch (err) {
    setStatus(false);
    wrap.className = "history-table-wrap visible";
    wrap.innerHTML = `<div class="empty-state" style="color:var(--red)">ERRO: ${escHtml(err.message)}</div>`;
  } finally {
    btn.classList.remove("loading");
    btn.textContent = btn.dataset.original || "CARREGAR HISTÓRICO";
  }
});


document.addEventListener("keydown", (e) => {
  if (e.key !== "Enter") return;
  const panel = $(".panel.active");
  if (!panel) return;
  const btn = panel.querySelector(".calc-btn");
  if (btn && !btn.classList.contains("loading")) btn.click();
});


$("#btn-deletar").addEventListener("click", async () => {
  const btn = $("#btn-deletar");
  const wrap = $("#res-historico");

  if (!confirm("Tem certeza que deseja deletar todo o histórico?")) return;

  btn.classList.add("loading");
  btn.dataset.original = btn.textContent;
  btn.innerHTML = `<span class="spinner"></span> DELETANDO...`;

  try {
    const data = await apiFetch("DELETE", "/deletar-historicos");
    setStatus(true);
    wrap.className = "history-table-wrap visible";
    wrap.innerHTML = `
      <div class="empty-state" style="color:var(--green)">
        ✓ ${escHtml(data?.messege ?? "Histórico deletado com sucesso.")}
      </div>`;
  } catch (err) {
    setStatus(false);
    wrap.className = "history-table-wrap visible";
    wrap.innerHTML = `<div class="empty-state" style="color:var(--red)">ERRO: ${escHtml(err.message)}</div>`;
  } finally {
    btn.classList.remove("loading");
    btn.textContent = btn.dataset.original || "DELETAR HISTÓRICO";
  }
});