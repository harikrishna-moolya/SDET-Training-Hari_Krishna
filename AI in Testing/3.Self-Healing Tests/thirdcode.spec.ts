import { test } from '@playwright/test';

test('Self-healing locator POC - TutorialsNinja Login', async ({ page }) => {

  // Navigate to Login page
  await page.goto(
    'https://tutorialsninja.com/demo/index.php?route=account/login'
  );

  // Enter credentials
  await page.fill('#input-email', 'hk01@gmail.com');
  await page.fill('#input-password', '1432');

  // Multiple fallback locators for Login button
  const loginButtonLocators = [
    'input[value="Login"]',                     // Primary locator
    'button:has-text("Login")',                 // Text-based
    'input.btn-primary',                        // CSS class
    'xpath=//input[@type="submit"]'             // XPath fallback
  ];

  let clicked = false;

  // Self-healing logic
  for (const locator of loginButtonLocators) {
    try {
      await page.locator(locator).click({ timeout: 2000 });
      console.log(`Clicked using locator: ${locator}`);
      clicked = true;
      break;
    } catch (error) {
      console.log(`Locator failed: ${locator}`);
    }
  }

  // Fail test if no locator works
  if (!clicked) {
    throw new Error('Login button not found using any fallback locator');
  }

});
