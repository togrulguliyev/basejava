package storage;

import model.Resume;

import java.util.Arrays;
/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage;
    private int size;

    public ArrayStorage(){
        this.storage = new Resume[10000];
    }

    private boolean containsResume(String uuid){
        for (int i = 0; i < size; i++){
            if (storage[i].getUuid().equals(uuid)){
                return true;
            }
        }
         return false;
    }

    public void update(Resume updatedResume){
        String uuid = updatedResume.getUuid();
        if (containsResume(updatedResume.getUuid())){
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    storage[i] = updatedResume;
                    return;
                }
            }
        }

        System.out.println("Storage does not contain this resume.");
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (containsResume(r.getUuid())){
            System.out.println("Storage contains this resume.");
            return;
        }
        if (size >= 10000){
            System.out.println("Storage overflow.");
            return;
        }

        storage[size] = r;
        size++;
    }

    public Resume get(String uuid) {
        if (containsResume(uuid)){
            for (int i = 0; i < size; i++){
                if (storage[i].getUuid().equals(uuid)){
                    return storage[i];
                }
            }
        }

        System.out.println("Storage does not contain this resume.");
        return null;
    }

    public void delete(String uuid) {
        if (containsResume(uuid)){
            for (int i = 0; i < size; i++){
                if (storage[i].getUuid().equals(uuid)){
                    storage[i] = storage[size - 1];
                    storage[size - 1] = null;
                    size--;
                    return;
                }
            }
        }

        System.out.println("Storage does not contain this resume.");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int getSize() {
        return size;
    }
}
