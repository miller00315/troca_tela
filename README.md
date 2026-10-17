# TrocaTela

Projeto de estudo sobre **troca de telas (navegação entre Activities)** no Android, usando Kotlin, layouts XML e `Intent`.

## Fluxo das telas

```
MainActivity  ──[Lançamento]──▶  LaunchActivity  ──[Confirmar]──▶  ConfirmActivity
                                       │
                                       └──[Listar]──▶  (a implementar)
```

| Tela | Layout | O que faz |
|------|--------|-----------|
| `MainActivity` | `activity_main.xml` | Tela inicial (launcher). Tem o botão **Lançamento**, que abre a `LaunchActivity`. |
| `LaunchActivity` | `activity_launch.xml` | Formulário com **Código**, **Quantidade** e **Valor**. O botão **Confirmar** abre a `ConfirmActivity`; o botão **Listar** ainda não tem ação. |
| `ConfirmActivity` | `activity_confirm.xml` | Exibe os dados lançados para conferência, com um botão **Confirmar**. |

## Conceitos estudados

### 1. Declarar as Activities no Manifest

Toda Activity precisa estar registrada em `AndroidManifest.xml`. Só a tela inicial tem o `intent-filter` de `MAIN`/`LAUNCHER`; as demais usam `android:exported="false"` porque só são abertas de dentro do próprio app.

```xml
<activity android:name=".MainActivity" android:exported="true">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />
        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>
<activity android:name=".LaunchActivity" android:exported="false" />
<activity android:name=".ConfirmActivity" android:exported="false" />
```

### 2. Abrir outra tela com uma `Intent` explícita

```kotlin
val intent = Intent(this, LaunchActivity::class.java)
startActivity(intent)
```

A nova Activity é empilhada sobre a atual (*back stack*). O botão **Voltar** do sistema a remove e retorna à tela anterior.

### 3. Fechar a tela atual

Para voltar programaticamente (por exemplo, após confirmar), basta chamar:

```kotlin
finish()
```

## Próximos passos

- [ ] **Passar dados entre telas**: enviar código, quantidade e valor da `LaunchActivity` para a `ConfirmActivity` com `intent.putExtra(...)` e ler com `intent.getStringExtra(...)` / `getIntExtra(...)` / `getDoubleExtra(...)`.
- [ ] **Exibir os dados** nos `TextView`s `tvConfirmCode`, `tvConfirmQuantity` e `tvConfirmValue`.
- [ ] **Validar os campos** antes de trocar de tela (campos vazios, valores inválidos).
- [ ] **Devolver resultado** da `ConfirmActivity` para a tela anterior com `registerForActivityResult` + `setResult(...)` + `finish()`.
- [ ] **Implementar a tela de listagem** (botão **Listar**), por exemplo com `RecyclerView`.

Exemplo de envio e leitura de dados:

```kotlin
// LaunchActivity
val intent = Intent(this, ConfirmActivity::class.java).apply {
    putExtra("code", edCode.text.toString())
    putExtra("quantity", edQuantity.text.toString())
    putExtra("value", edValue.text.toString())
}
startActivity(intent)

// ConfirmActivity
tvConfirmCode.text = intent.getStringExtra("code")
tvConfirmQuantity.text = intent.getStringExtra("quantity")
tvConfirmValue.text = intent.getStringExtra("value")
```

## Estrutura

```
app/src/main/
├── AndroidManifest.xml
├── java/com/miller/oliveira/trocatela/
│   ├── MainActivity.kt
│   ├── LaunchActivity.kt
│   └── ConfirmActivity.kt
└── res/
    ├── layout/
    │   ├── activity_main.xml
    │   ├── activity_launch.xml
    │   └── activity_confirm.xml
    └── values/strings.xml
```

## Requisitos e execução

- Android Studio recente (Android Gradle Plugin 9.x)
- `minSdk` 33 · `targetSdk`/`compileSdk` 37
- Kotlin, Views/XML com `ConstraintLayout`, AppCompat e Material Components

Abra o projeto no Android Studio e rode em um emulador ou dispositivo, ou pela linha de comando:

```bash
./gradlew installDebug
```
