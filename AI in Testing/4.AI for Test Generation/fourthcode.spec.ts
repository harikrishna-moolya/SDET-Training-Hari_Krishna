import { test, expect } from '@playwright/test';

test.describe('TutorialsNinja Login Tests', () => {

  const APP_URL =
    'https://tutorialsninja.com/demo/index.php?route=account/login';

  const validUser = {
    email: 'hk01@gmail.com',
    password: '1432'
  };

  // 1. Login page should load successfully
  test('Login page should load', async ({ page }) => {
    await page.goto(APP_URL);

    await expect(page).toHaveURL(/account\/login/);
    await expect(page.locator('h2')).toContainText('Returning Customer');
  });

  // 2. Login with valid credentials
  test('Login with valid credentials', async ({ page }) => {
    await page.goto(APP_URL);

    await page.fill('#input-email', validUser.email);
    await page.fill('#input-password', validUser.password);
    await page.click('input[value="Login"]');

    await expect(page).toHaveURL(/account\/account/);
    await expect(page.locator('h2')).toContainText('My Account');
  });

  // 3. Login with invalid credentials
  test('Login with invalid credentials', async ({ page }) => {
    await page.goto(APP_URL);

    await page.fill('#input-email', 'wrong@gmail.com');
    await page.fill('#input-password', 'wrong123');
    await page.click('input[value="Login"]');

    await expect(
      page.locator('.alert-danger')
    ).toContainText('Warning: No match for E-Mail Address and/or Password.');
  });

  // 4. Login with empty fields
  test('Login with empty fields', async ({ page }) => {
    await page.goto(APP_URL);

    await page.click('input[value="Login"]');

    await expect(
      page.locator('.alert-danger')
    ).toBeVisible();
  });

});
