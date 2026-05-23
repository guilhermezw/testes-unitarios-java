const BASE_URL = 'http://localhost:8080';
const TOKEN_KEY = '_sid';

const getToken   = () => sessionStorage.getItem(TOKEN_KEY);
const saveToken  = t  => sessionStorage.setItem(TOKEN_KEY, t);
const clearToken = () => sessionStorage.removeItem(TOKEN_KEY);

async function apiFetch(url, options = {}) {
  let res;
  try {
    res = await fetch(url, options);
  } catch {
    throw new Error('ERR_CONN: Servidor inacessível. Verifique se a API está online e se o CORS está habilitado.');
  }

  const contentType = res.headers.get('content-type') || '';
  let body;
  if (contentType.includes('application/json')) {
    body = await res.json().catch(() => ({}));
  } else {
    body = await res.text().catch(() => '');
  }

  if (!res.ok) throw new Error(extractSpringError(body, res.status));
  return body;
}

function extractSpringError(body, status) {
  if (!body) return `ERR_${status}`;
  if (typeof body === 'string') return body.length < 400 ? body : `ERR_${status}`;
  if (Array.isArray(body.errors) && body.errors.length)
    return body.errors.map(e => e.defaultMessage || e.message || JSON.stringify(e)).join(' · ');
  if (Array.isArray(body.fieldErrors) && body.fieldErrors.length)
    return body.fieldErrors.map(e => e.defaultMessage || e.message || JSON.stringify(e)).join(' · ');
  if (Array.isArray(body.violations) && body.violations.length)
    return body.violations.map(v => v.message || JSON.stringify(v)).join(' · ');
  if (body.message) return body.message;
  if (body.detail)  return body.detail;
  if (body.title)   return body.title;
  if (body.error)   return body.error;
  return `ERR_${status}`;
}

async function apiLogin(email, senha) {
  return apiFetch(`${BASE_URL}/auth/login`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ email, senha }),
  });
}

async function apiCadastro(payload) {
  return apiFetch(`${BASE_URL}/auth/cadastro`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(payload),
  });
}

async function apiMensagem() {
  return apiFetch(`${BASE_URL}/view/mensagem`, {
    headers: { Authorization: `Bearer ${getToken()}` },
  });
}

async function apiPerfil() {
  return apiFetch(`${BASE_URL}/view/perfil`, {
    headers: { Authorization: `Bearer ${getToken()}` },
  });
}

function typewriter(el, text, speed = 20) {
  el.textContent = '';
  let i = 0;
  return new Promise(resolve => {
    const tick = () => {
      if (i < text.length) {
        el.textContent += text[i++];
        setTimeout(tick, speed + Math.random() * 18);
      } else {
        resolve();
      }
    };
    tick();
  });
}

function showView(name) {
  const target = document.getElementById(`view-${name}`);
  if (!target) return;
  document.body.classList.add('screen-transition');
  setTimeout(() => {
    document.querySelectorAll('.view').forEach(v => v.classList.remove('active'));
    target.classList.add('active');
    document.body.classList.remove('screen-transition');
  }, 90);
}

(function clock() {
  const els = [document.getElementById('term-time'), document.getElementById('term-time-cad')];
  const tick = () => {
    const t = new Date().toLocaleTimeString('pt-BR', { hour12: false });
    els.forEach(el => { if (el) el.textContent = t; });
    setTimeout(tick, 1000);
  };
  tick();
})();

const EMAIL_RE = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
const SENHA_RE = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&.#_-])[A-Za-z\d@$!%*?&.#_-]{10,12}$/;

function setFieldError(inputEl, errorEl, msg) {
  const wrap = inputEl.closest('.input-wrap');
  if (wrap) wrap.classList.toggle('error', !!msg);
  errorEl.textContent = msg ? `> ${msg}` : '';
}

function validateLogin() {
  const email    = document.getElementById('login-email');
  const senha    = document.getElementById('login-senha');
  const errEmail = document.getElementById('err-login-email');
  const errSenha = document.getElementById('err-login-senha');
  let ok = true;

  if (!email.value.trim()) {
    setFieldError(email, errEmail, 'E-mail é obrigatório.'); ok = false;
  } else if (!EMAIL_RE.test(email.value.trim())) {
    setFieldError(email, errEmail, 'Formato de e-mail inválido.'); ok = false;
  } else {
    setFieldError(email, errEmail, '');
  }

  if (!senha.value) {
    setFieldError(senha, errSenha, 'Senha é obrigatória.'); ok = false;
  } else {
    setFieldError(senha, errSenha, '');
  }

  return ok;
}

function validateCadastro() {
  const nome     = document.getElementById('cad-nome');
  const email    = document.getElementById('cad-email');
  const senha    = document.getElementById('cad-senha');
  const role     = document.getElementById('cad-role');
  const errNome  = document.getElementById('err-cad-nome');
  const errEmail = document.getElementById('err-cad-email');
  const errSenha = document.getElementById('err-cad-senha');
  const errRole  = document.getElementById('err-cad-role');
  let ok = true;

  if (!nome.value.trim()) {
    setFieldError(nome, errNome, 'Nome é obrigatório.'); ok = false;
  } else { setFieldError(nome, errNome, ''); }

  if (!email.value.trim()) {
    setFieldError(email, errEmail, 'E-mail é obrigatório.'); ok = false;
  } else if (!EMAIL_RE.test(email.value.trim())) {
    setFieldError(email, errEmail, 'Formato de e-mail inválido.'); ok = false;
  } else { setFieldError(email, errEmail, ''); }

  if (!senha.value) {
    setFieldError(senha, errSenha, 'Senha é obrigatória.'); ok = false;
  } else if (!SENHA_RE.test(senha.value)) {
    setFieldError(senha, errSenha, 'Deve ter 10–12 chars, maiúscula, minúscula, número e especial (@$!%*?&.#_-).'); ok = false;
  } else { setFieldError(senha, errSenha, ''); }

  if (!role.value) {
    setFieldError(role, errRole, 'Selecione um perfil de acesso.'); ok = false;
  } else { setFieldError(role, errRole, ''); }

  return ok;
}

function calcStrength(pw) {
  if (!pw) return { level: '', label: '' };
  const score = [
    /[a-z]/.test(pw),
    /[A-Z]/.test(pw),
    /\d/.test(pw),
    /[@$!%*?&.#_-]/.test(pw),
    pw.length >= 10,
  ].filter(Boolean).length;
  if (score <= 2) return { level: 'weak',   label: 'FRACA'  };
  if (score <= 3) return { level: 'medium', label: 'MÉDIA'  };
  return             { level: 'strong', label: 'FORTE'  };
}

document.getElementById('cad-senha').addEventListener('input', function () {
  const { level, label } = calcStrength(this.value);
  document.getElementById('pw-strength').className = `pw-strength ${level}`;
  document.getElementById('pw-label').textContent = label;
});

document.querySelectorAll('.toggle-pw').forEach(btn => {
  btn.addEventListener('click', function () {
    const input = document.getElementById(this.dataset.target);
    const isText = input.type === 'text';
    input.type = isText ? 'password' : 'text';
    this.classList.toggle('active', !isText);
  });
});

function showAlert(el, msg, type = 'error') {
  el.textContent = `> ${msg}`;
  el.className = `alert ${type}`;
}
function hideAlert(el) {
  el.className = 'alert hidden';
  el.textContent = '';
}

function setLoading(btn, loading) {
  btn.disabled = loading;
  btn.querySelector('.btn-text').classList.toggle('hidden', loading);
  btn.querySelector('.btn-loader').classList.toggle('hidden', !loading);
}

document.querySelectorAll('[data-goto]').forEach(btn => {
  btn.addEventListener('click', () => showView(btn.dataset.goto));
});

document.getElementById('form-login').addEventListener('submit', async function (e) {
  e.preventDefault();
  const alertEl = document.getElementById('login-alert');
  hideAlert(alertEl);
  if (!validateLogin()) return;

  const btn   = document.getElementById('btn-login');
  const email = document.getElementById('login-email').value.trim();
  const senha = document.getElementById('login-senha').value;

  setLoading(btn, true);
  try {
    const data = await apiLogin(email, senha);
    if (!data.token) throw new Error('Token não recebido. Verifique as credenciais.');
    if (data.message) {
      showAlert(alertEl, data.message, 'success');
      await new Promise(r => setTimeout(r, 900));
    }
    saveToken(data.token);
    await loadDashboard();
    showView('dashboard');
  } catch (err) {
    showAlert(alertEl, err.message || 'Falha ao fazer login. Tente novamente.');
  } finally {
    setLoading(btn, false);
  }
});

document.getElementById('form-cadastro').addEventListener('submit', async function (e) {
  e.preventDefault();
  const alertEl = document.getElementById('cadastro-alert');
  hideAlert(alertEl);
  if (!validateCadastro()) return;

  const btn = document.getElementById('btn-cadastro');
  setLoading(btn, true);
  try {
    const payload = {
      nome:  document.getElementById('cad-nome').value.trim(),
      email: document.getElementById('cad-email').value.trim(),
      senha: document.getElementById('cad-senha').value,
      role:  document.getElementById('cad-role').value,
    };
    const data = await apiCadastro(payload);
    showAlert(alertEl, data.message || 'Usuário criado com sucesso!', 'success');

    this.reset();
    document.getElementById('pw-strength').className = 'pw-strength';
    document.getElementById('pw-label').textContent = '';

    setTimeout(() => {
      hideAlert(alertEl);
      showView('login');
    }, 2200);
  } catch (err) {
    showAlert(alertEl, err.message || 'Erro ao cadastrar. Tente novamente.');
  } finally {
    setLoading(btn, false);
  }
});

async function loadDashboard() {
  document.getElementById('perfil-loading').classList.remove('hidden');
  document.getElementById('perfil-content').classList.add('hidden');
  document.getElementById('msg-loading').classList.remove('hidden');
  document.getElementById('server-msg').classList.add('hidden');

  const [perfilResult, msgResult] = await Promise.allSettled([apiPerfil(), apiMensagem()]);

  document.getElementById('perfil-loading').classList.add('hidden');
  const perfilContent = document.getElementById('perfil-content');

  if (perfilResult.status === 'fulfilled') {
    const p = perfilResult.value;
    const nome  = p.nome  ?? 'Usuário';
    const email = p.email ?? '—';
    const role  = p.role  ?? '';

    document.getElementById('perfil-nome').textContent  = nome;
    document.getElementById('perfil-email').textContent = email;
    document.getElementById('perfil-role').textContent  = String(role).toUpperCase();
    document.getElementById('avatar-inicial').textContent = nome.trim().charAt(0).toUpperCase();
    perfilContent.classList.remove('hidden');
  } else {
    perfilContent.innerHTML = `<p class="dash-error">> ${perfilResult.reason?.message || 'Erro ao carregar perfil.'}</p>`;
    perfilContent.classList.remove('hidden');
  }

  document.getElementById('msg-loading').classList.add('hidden');
  const msgEl = document.getElementById('server-msg');

  if (msgResult.status === 'fulfilled') {
    const txt = typeof msgResult.value === 'string'
      ? msgResult.value
      : JSON.stringify(msgResult.value);
    msgEl.classList.remove('hidden');
    await typewriter(msgEl, txt);
  } else {
    msgEl.textContent = `${msgResult.reason?.message || 'Erro ao carregar mensagem.'}`;
    msgEl.classList.remove('hidden');
    msgEl.classList.add('dash-error');
  }
}

document.getElementById('btn-logout').addEventListener('click', () => {
  clearToken();
  showView('login');
});

(async function init() {
  if (getToken()) {
    try {
      await loadDashboard();
      showView('dashboard');
    } catch {
      clearToken();
      showView('login');
    }
  } else {
    showView('login');
  }
})();