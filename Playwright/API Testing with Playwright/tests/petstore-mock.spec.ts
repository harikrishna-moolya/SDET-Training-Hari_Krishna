import { test, expect } from '@playwright/test';

test('Mock Get User API', async ({ page }) => {

  await page.route('**/v2/user/Hari', route => {
    route.fulfill({
      status: 200,
      contentType: 'application/json',
      body: JSON.stringify({
        id: 101,
        username: 'Hari',
        firstName: 'MockHari',
        email: 'mock@gmail.com'
      })
    });
  });

  await page.goto('https://petstore.swagger.io/v2/user/Hari');

  const content = await page.content();
  expect(content).toContain('MockHari');
});
