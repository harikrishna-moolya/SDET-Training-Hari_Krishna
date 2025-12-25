import { test, expect } from '@playwright/test';

const BASE_URI = 'https://petstore.swagger.io/v2';

const userPayload = {
  id: 101,
  username: 'Hari',
  firstName: 'Hari',
  lastName: 'Krishna',
  email: 'hari@gmail.com',
  password: 'HK@123',
  phone: '9999999999',
  userStatus: 1
};

test.describe('Petstore User CRUD APIs', () => {


  // POST – Create User

  test('POST | Create user', async ({ request }) => {
    try {
      const response = await request.post(`${BASE_URI}/user`, {
        data: userPayload
      });

      expect(response.status()).toBe(200);

      const body = await response.json();
      expect(body.message).toBe(String(userPayload.id));

    } catch (error) {
      console.error('POST Create User Failed:', error);
      throw error;
    }
  });

  
  // GET – Fetch User

  test('GET | Fetch user by username', async ({ request }) => {
    try {
      const response = await request.get(
        `${BASE_URI}/user/${userPayload.username}`
      );

      expect(response.status()).toBe(200);

      const body = await response.json();
      expect(body.username).toBe('Hari');
      expect(body.firstName).toBe('Hari');
      expect(body.email).toBe('hari@gmail.com');

    } catch (error) {
      console.error('GET User Failed:', error);
      throw error;
    }
  });

  // PUT – Update User

  test('PUT | Update user details', async ({ request }) => {
    try {
      const updatedPayload = {
        ...userPayload,
        firstName: 'HariUpdated',
        email: 'hari.updated@gmail.com'
      };

      const response = await request.put(
        `${BASE_URI}/user/${userPayload.username}`,
        { data: updatedPayload }
      );

      expect(response.status()).toBe(200);

      const body = await response.json();
      expect(body.message).toBe(String(userPayload.id));

    } catch (error) {
      console.error('PUT Update User Failed:', error);
      throw error;
    }
  });


  // DELETE – Delete User

  test('DELETE | Delete user', async ({ request }) => {
    try {
      const response = await request.delete(
        `${BASE_URI}/user/${userPayload.username}`
      );

      expect(response.status()).toBe(200);

      const body = await response.json();
      expect(body.message).toBe(userPayload.username);

    } catch (error) {
      console.error('DELETE User Failed:', error);
      throw error;
    }
  });

});
