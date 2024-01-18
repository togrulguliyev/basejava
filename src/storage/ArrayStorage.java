package storage;

import model.Resume;

import java.util.Arrays;
/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static final int STORAGE_LIMIT = 10000;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size;

    public int getSize() {
        return size;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public void update(Resume updatedResume){
        String uuid = updatedResume.getUuid();
        int index = getIndex(uuid);
        if (isExisting(index)){
            storage[index] = updatedResume;
            return;
        }

        System.out.println("Storage does not contain this resume.");
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        String uuid = r.getUuid();
        int index = getIndex(uuid);
        if (isExisting(index)){
            System.out.println("Storage contains this resume.");
            return;
        }
        if (size >= STORAGE_LIMIT){
            System.out.println("Storage overflow.");
            return;
        }

        storage[size] = r;
        size++;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (isExisting(index)){
            return storage[index];
        }

        System.out.println("Storage does not contain this resume.");
        return null;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (isExisting(index)){
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }

        System.out.println("Storage does not contain this resume.");
    }

    private int getIndex(String uuid){
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
    private boolean isExisting(int index){
        return (index != -1);
    }
}
